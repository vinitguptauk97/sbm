package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class PricePerQuantityTypeTest {
    @Test
    public void equals_check_when_prices_are_different() {
        PricePerQuantityType pricePerQuantityType1 = PricePerQuantityType.of(CurrencyUnit.GBP, 359);
        PricePerQuantityType pricePerQuantityType2 = PricePerQuantityType.of(CurrencyUnit.GBP, 322);
        assertNotEquals(pricePerQuantityType1, pricePerQuantityType2);
    }

    @Test
    public void equals_check_when_currencies_are_different() {
        PricePerQuantityType pricePerQuantityType1 = PricePerQuantityType.of(CurrencyUnit.AUD, 359);
        PricePerQuantityType pricePerQuantityType2 = PricePerQuantityType.of(CurrencyUnit.GBP, 359);
        assertNotEquals(pricePerQuantityType1, pricePerQuantityType2);
    }
}
