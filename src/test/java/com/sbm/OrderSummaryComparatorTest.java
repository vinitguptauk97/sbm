package com.sbm;

import com.sbm.model.Order;
import com.sbm.model.OrderSummary;
import com.sbm.model.PricePerQuantityType;
import com.sbm.model.Quantity;
import org.joda.money.CurrencyUnit;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class OrderSummaryComparatorTest {

    @Test
    public void should_compare_to_0_for_orders_of_same_type_and_price() {
        Comparator<OrderSummary> byOrderTypeAndOrderPrice = new OrderSummaryComparator();
        OrderSummary orderSummary1 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.SELL, new Quantity(3.2, Quantity.QuantityType.KILOGRAMS));
        OrderSummary orderSummary2 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.SELL, new Quantity(3.7, Quantity.QuantityType.KILOGRAMS));
        assertEquals(0, byOrderTypeAndOrderPrice.compare(orderSummary1, orderSummary2));
    }

    @Test
    public void should_sort_BUY_OrderSummary_in_descending_order() {
        Comparator<OrderSummary> byOrderTypeAndOrderPrice = new OrderSummaryComparator();
        OrderSummary orderSummary1 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY, new Quantity(3.2, Quantity.QuantityType.KILOGRAMS));
        OrderSummary orderSummary2 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.BUY, new Quantity(3.5, Quantity.QuantityType.KILOGRAMS));
        assertEquals(-1, byOrderTypeAndOrderPrice.compare(orderSummary1, orderSummary2));
    }

    @Test
    public void should_sort_SELL_OrderSummary_in_ascending_order() {
        Comparator<OrderSummary> byOrderTypeAndOrderPrice = new OrderSummaryComparator();
        OrderSummary orderSummary1 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.SELL, new Quantity(3.2, Quantity.QuantityType.KILOGRAMS));
        OrderSummary orderSummary2 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.SELL, new Quantity(3.9, Quantity.QuantityType.KILOGRAMS));
        assertEquals(1, byOrderTypeAndOrderPrice.compare(orderSummary1, orderSummary2));
    }

    @Test
    public void should_sort_BUY_orders_first_over_SELL_orders() {
        Comparator<OrderSummary> byOrderTypeAndOrderPrice = new OrderSummaryComparator();
        OrderSummary orderSummary1 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY, new Quantity(3.2, Quantity.QuantityType.KILOGRAMS));
        OrderSummary orderSummary2 = new OrderSummary(PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.SELL, new Quantity(4.2, Quantity.QuantityType.KILOGRAMS));
        assertEquals(-1, byOrderTypeAndOrderPrice.compare(orderSummary1, orderSummary2));
    }
}
