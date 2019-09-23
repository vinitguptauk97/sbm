package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class OrderTest {

    @Test
    public void should_create_different_orders_from_same_user_even_if_2_orders_are_of_same_value() {
        Order order1 = new Order(1, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        Order order2 = new Order(2, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        assertNotEquals(order1, order2);
    }

}
