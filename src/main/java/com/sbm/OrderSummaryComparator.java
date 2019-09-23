package com.sbm;

import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

import java.util.Comparator;

/**
 * provides external comparator for OrderSummary objects.
 */

public class OrderSummaryComparator implements Comparator<OrderSummary> {

    /**
     * 1. compares Order's OrderType and sorts them alphabetically i.e. (BUY,BUY,BUY,SELL,SELL....)
     * 2. compares and sort SELL Orders in ascending order.
     * 3. compares and sort BUY Orders in descending order.
     *
     * @param left
     * @param right
     * @return 0, 1 or -1
     */
    @Override
    public int compare(OrderSummary left, OrderSummary right) {
        if (left.getOrderType() != right.getOrderType()) {
            return left.getOrderType().compareTo(right.getOrderType());
        }

        if (left.getOrderType() == Order.Type.SELL) {
            return left.getPricePerQuantityType().compareTo(right.getPricePerQuantityType());
        }

        if (left.getOrderType() == Order.Type.BUY) {
            return right.getPricePerQuantityType().compareTo(left.getPricePerQuantityType());
        }

        return 0;
    }
}
