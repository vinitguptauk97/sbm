package com.sbm.model;

import java.util.Objects;

public final class Bid {
    private final PricePerQuantityType pricePerQuantityType;
    private final Order.Type orderType;

    public Bid(PricePerQuantityType pricePerQuantityType, Order.Type orderType) {
        this.pricePerQuantityType = pricePerQuantityType;
        this.orderType = orderType;
    }

    public static Bid ofOrder(Order order) {
        return new Bid(order.getPricePerQuantityType(), order.getOrderType());
    }

    public PricePerQuantityType getPricePerQuantityType() {
        return pricePerQuantityType;
    }

    public Order.Type getOrderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Objects.equals(pricePerQuantityType, bid.pricePerQuantityType) &&
                orderType == bid.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerQuantityType, orderType);
    }

    @Override
    public String toString() {
        return "Bid{" +
                "pricePerQuantityType=" + pricePerQuantityType +
                ", orderType=" + orderType +
                '}';
    }
}