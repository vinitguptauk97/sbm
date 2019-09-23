package com.sbm;

import com.sbm.model.*;
import org.joda.money.CurrencyUnit;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * Implements live order board to register and cancel order and provide Live Order Summary.
 * <p>
 * Using OrderId in Order to uniquely identify the Order as same user can add 2 or more different orders with same price
 * and quantity.
 * <p>
 * OrderId generation is thread safe with assumption of low contention among different threads.
 * <p>
 * Traversal of Orders to get OrderSummary by OrderType and Price will give current view of Orders at or after creation
 * of Iterator but will not throw ConcurrentModificationException, in case Order Cancellation happens at the same time.
 */
public class LiveOrderBoardImpl implements LiveOrderBoard {

    private final AtomicLong orderIdGenerator = new AtomicLong(1);
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Comparator<OrderSummary> byOrderTypeAndOrderPrice = new OrderSummaryComparator();

    @Override
    public long registerOrder(String userId, Order.Type orderType, double quantity, double price) {
        assertParameters(userId, orderType, quantity, price);
        User user = users.computeIfAbsent(userId, id -> new User(id));
        Order order = new Order(orderIdGenerator.getAndIncrement(), user, new Quantity(quantity,
                Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, price), orderType);
        orders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    @Override
    public void cancelOrder(long orderId) {
        orders.remove(orderId);
    }

    /**
     * Orders are first grouped by Bid(Price and OrderType) to get list of Quantity
     * and then converted into OrderSummary object to get total quantity of order at same price
     * and with same orderType.
     * OrderSummary objects are then sorted as first Buy orders in descending Order
     * and then SELL orders in ascending order.
     * <p>
     * NOTE:- groupingBy and map operation could be in Parallel (concurrently) provided number of orders
     * are quite big. (Profiling needs to be done to ascertain the advantage/need of it as per requirement)
     *
     * @return sorted list of OrderSummary objects
     */
    @Override
    public List<OrderSummary> summary() {
        return orders.values().stream().parallel().
                collect(groupingBy(Bid::ofOrder, mapping(Order::getQuantity, toList()))).
                entrySet().stream().parallel().
                map(toOrderSummary()).
                sorted(byOrderTypeAndOrderPrice).
                collect(toList());
    }

    private Function<Map.Entry<Bid, List<Quantity>>, OrderSummary> toOrderSummary() {
        return entry -> new OrderSummary(entry.getKey(), entry.getValue());
    }

    private void assertParameters(String userId, Order.Type orderType, double quantity, double price) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId can't be null.");
        } else if (orderType == null) {
            throw new IllegalArgumentException("OrderType can't be null.");
        } else if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be 0 or negative.");
        } else if (price < 0.0) {
            throw new IllegalArgumentException("Price can't be negative.");
        }
    }
}
