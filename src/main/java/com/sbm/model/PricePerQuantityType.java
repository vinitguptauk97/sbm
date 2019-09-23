package com.sbm.model;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.Objects;

public final class PricePerQuantityType implements Comparable<PricePerQuantityType> {

    private final Money amount;

    public PricePerQuantityType(Money amount) {
        this.amount = amount;
    }

    public static PricePerQuantityType of(CurrencyUnit currency, double amount) {
        return new PricePerQuantityType(Money.of(currency, amount));
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PricePerQuantityType{" +
                "amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricePerQuantityType that = (PricePerQuantityType) o;
        return Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public int compareTo(PricePerQuantityType o) {
        return amount.compareTo(o.amount);
    }
}