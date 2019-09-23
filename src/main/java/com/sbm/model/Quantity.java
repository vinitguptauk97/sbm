package com.sbm.model;

import java.util.Objects;

public final class Quantity {
    private final Double mass;
    private final QuantityType quantityType;
    public Quantity(Double mass, QuantityType quantityType) {
        this.mass = mass;
        this.quantityType = quantityType;
    }

    public Double getMass() {
        return mass;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(mass, quantity.mass) &&
                Objects.equals(quantityType, quantity.quantityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mass, quantityType);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "mass=" + mass +
                ", quantityType=" + quantityType +
                '}';
    }

    public enum QuantityType {KILOGRAMS}
}
