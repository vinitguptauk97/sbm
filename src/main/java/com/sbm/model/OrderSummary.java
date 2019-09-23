package com.sbm.model;

import java.util.List;
import java.util.Objects;

/**
 * It provides Order Summary for same Order Type(BUY, SELL) and PricePerQuantityType
 */
public final class OrderSummary {

    private final PricePerQuantityType pricePerQuantityType;
    private final Order.Type orderType;
    private final Quantity quantity;

    public OrderSummary(PricePerQuantityType pricePerQuantityType, Order.Type orderType, Quantity quantity) {
        this.pricePerQuantityType = pricePerQuantityType;
        this.orderType = orderType;
        this.quantity = quantity;
    }

    public OrderSummary(Bid bid, List<Quantity> quantities) {
        this(bid.getPricePerQuantityType(), bid.getOrderType(), total(quantities));
    }

    private static Quantity total(List<Quantity> quantities) {
        Double sum = quantities.stream().mapToDouble(Quantity::getMass).sum();
        return new Quantity(sum, Quantity.QuantityType.KILOGRAMS);
    }

    public PricePerQuantityType getPricePerQuantityType() {
        return pricePerQuantityType;
    }

    public Order.Type getOrderType() {
        return orderType;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return orderType + ":" + quantity.getMass() + " " + quantity.getQuantityType() + " for "
                + pricePerQuantityType.getAmount().getAmount().toString() + " "
                + pricePerQuantityType.getAmount().getCurrencyUnit().getCurrencyCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSummary that = (OrderSummary) o;
        return Objects.equals(pricePerQuantityType, that.pricePerQuantityType) &&
                orderType == that.orderType &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerQuantityType, orderType, quantity);
    }

}