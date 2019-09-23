package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BidTest {

    @Test
    public void bids_with_different_orderType_should_not_be_equal() {
        Order order1 = new Order(1, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        Bid bid1 = Bid.ofOrder(order1);
        Order order2 = new Order(2, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.SELL);
        Bid bid2 = Bid.ofOrder(order2);
        assertFalse(bid1.equals(bid2));
    }

    @Test
    public void bids_with_different_price_should_not_be_equal() {
        Order order1 = new Order(1, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        Bid bid1 = Bid.ofOrder(order1);
        Order order2 = new Order(2, new User("John1"), new Quantity(3.5, Quantity.QuantityType.KILOGRAMS), PricePerQuantityType.of(CurrencyUnit.GBP, 309), Order.Type.BUY);
        Bid bid2 = Bid.ofOrder(order2);
        assertFalse(bid1.equals(bid2));
    }
}
