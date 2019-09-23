package com.sbm;

import com.sbm.model.Order;
import com.sbm.model.OrderSummary;

import java.util.List;

/**
 * Implements live order board to register and cancel order and provide Live Order Summary.
 */
public interface LiveOrderBoard {

    long registerOrder(String userId, Order.Type orderType, double quantity, double price);

    void cancelOrder(long orderId);

    List<OrderSummary> summary();

}
