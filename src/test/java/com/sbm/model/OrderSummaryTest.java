package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderSummaryTest {

    @Test
    public void should_add_bids_of_same_price() {
        Bid bid = new Bid(PricePerQuantityType.of(CurrencyUnit.GBP, 311), Order.Type.BUY);
        List<Quantity> quantities = new ArrayList<>();
        quantities.add(new Quantity(3.2, Quantity.QuantityType.KILOGRAMS));
        quantities.add(new Quantity(1.2, Quantity.QuantityType.KILOGRAMS));
        quantities.add(new Quantity(5.4, Quantity.QuantityType.KILOGRAMS));
        OrderSummary orderSummary = new OrderSummary(bid, quantities);

        assertEquals(new Double(9.8), orderSummary.getQuantity().getMass());
    }
}
