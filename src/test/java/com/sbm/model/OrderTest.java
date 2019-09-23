package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertFalse;

public class OrderTest {

    private final AtomicLong orderIdGenerator = new AtomicLong(1);

    @Test
    public void should_create_different_orders_from_same_user_even_if_2_orders_are_of_same_value() {
        long orderId1 = orderIdGenerator.getAndIncrement();
        Order order1 = new Order(orderId1, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        long orderId2 = orderIdGenerator.getAndIncrement();
        Order order2 = new Order(orderId2, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        assertFalse(order1.equals(order2));
    }

}
