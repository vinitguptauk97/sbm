package com.sbm.model;

import java.util.Objects;

public final class Order {
    private final long orderId;
    private final User user;
    private final Quantity quantity;
    private final PricePerQuantityType pricePerQuantityType;
    private final Order.Type orderType;
    public Order(long orderId, User user, Quantity quantity, PricePerQuantityType pricePerQuantityType, Type orderType) {
        this.orderId = orderId;
        this.user = user;
        this.quantity = quantity;
        this.pricePerQuantityType = pricePerQuantityType;
        this.orderType = orderType;
    }

    public long getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public PricePerQuantityType getPricePerQuantityType() {
        return pricePerQuantityType;
    }

    public Type getOrderType() {
        return orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", measure=" + quantity +
                ", pricePerQuantityType=" + pricePerQuantityType +
                ", orderType=" + orderType +
                '}';
    }

    public enum Type {BUY, SELL}
}
