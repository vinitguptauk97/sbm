package com.sbm.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class QuantityTest {

    @Test
    public void quantity_with_different_mass_should_not_be_equal() {
        Quantity quantity1 = new Quantity(3.5, Quantity.QuantityType.KILOGRAMS);
        Quantity quantity2 = new Quantity(2.1, Quantity.QuantityType.KILOGRAMS);
        assertFalse(quantity1.equals(quantity2));
    }
}
