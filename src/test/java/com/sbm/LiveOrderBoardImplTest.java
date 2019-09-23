package com.sbm;

import com.sbm.model.Order;
import com.sbm.model.OrderSummary;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveOrderBoardImplTest {

    private LiveOrderBoard liveOrderBoard;

    @Before
    public void setup() {
        liveOrderBoard = new LiveOrderBoardImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_register_order_with_NULL_userId() {
        liveOrderBoard.registerOrder(null, Order.Type.BUY, 3.5, 307);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_register_order_with_ZERO_quantity() {
        liveOrderBoard.registerOrder("Alice", Order.Type.SELL, 0.0, 307);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_register_order_with_NEGATIVE_quantity() {
        liveOrderBoard.registerOrder("Alice", Order.Type.BUY, -5.5, 307);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_register_order_with_NEGATIVE_price() {
        liveOrderBoard.registerOrder("Alice", Order.Type.BUY, 3.5, -200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_register_order_with_null_orderType() {
        liveOrderBoard.registerOrder("Alice", null, 5.5, 307);
    }


    @Test
    public void should_register_order() {
        liveOrderBoard.registerOrder("Alice", Order.Type.BUY, 3.5, 307);
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(1, orderSummaries.size());
        assertEquals(new Double(3.5), orderSummaries.get(0).getQuantity().getMass());
        assertEquals("307.00", orderSummaries.get(0).getPricePerQuantityType().getAmount().getAmount().toString());
        assertEquals(Order.Type.BUY, orderSummaries.get(0).getOrderType());
    }

    @Test
    public void should_cancel_registered_order() {
        long orderId = liveOrderBoard.registerOrder("John", Order.Type.BUY, 3.5, 307);

        assertEquals(1, liveOrderBoard.summary().size());

        liveOrderBoard.cancelOrder(orderId);

        assertEquals(0, liveOrderBoard.summary().size());
    }

    @Test
    public void should_not_cancel_un_registered_order() {
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 3.5, 307);

        assertEquals(1, liveOrderBoard.summary().size());

        liveOrderBoard.cancelOrder(5);

        assertEquals(1, liveOrderBoard.summary().size());
    }


    @Test
    public void should_display_0_orders_when_no_orders_registered() {
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(0, orderSummaries.size());
    }

    @Test
    public void should_display_registered_orders_summary_in_proper_string_format() {
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 3.5, 307);
        liveOrderBoard.registerOrder("Test1", Order.Type.BUY, 3.5, 306);
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 2.9, 306);
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(2, orderSummaries.size());
        //first orderSummary
        assertEquals("BUY:3.5 KILOGRAMS for 307.00 GBP", orderSummaries.get(0).toString());
        //second orderSummary
        assertEquals("BUY:6.4 KILOGRAMS for 306.00 GBP", orderSummaries.get(1).toString());
    }

    @Test
    public void should_display_registered_BUY_orders_summary_in_descending_order() {
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 3.5, 307);
        liveOrderBoard.registerOrder("Test1", Order.Type.BUY, 3.5, 306);
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 2.9, 306);
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(2, orderSummaries.size());

        //first orderSummary
        assertEquals(new Double(3.5), orderSummaries.get(0).getQuantity().getMass());
        assertEquals(new Double(307), new Double(orderSummaries.get(0).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

        //second orderSummary
        assertEquals(new Double(6.4), orderSummaries.get(1).getQuantity().getMass());
        assertEquals(new Double(306), new Double(orderSummaries.get(1).getPricePerQuantityType().getAmount().getAmount().doubleValue()));
    }

    @Test
    public void should_display_registered_SELL_orders_summary_in_ascending_order() {
        liveOrderBoard.registerOrder("Rob", Order.Type.SELL, 3.5, 306);
        liveOrderBoard.registerOrder("Rob", Order.Type.SELL, 1, 309);
        liveOrderBoard.registerOrder("Alice", Order.Type.SELL, 2.0, 306);
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(2, orderSummaries.size());

        //first SELL orderSummary
        assertEquals(new Double(5.5), orderSummaries.get(0).getQuantity().getMass());
        assertEquals(new Double(306), new Double(orderSummaries.get(0).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

        //second SELL orderSummary
        assertEquals(new Double(1), orderSummaries.get(1).getQuantity().getMass());
        assertEquals(new Double(309), new Double(orderSummaries.get(1).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

    }

    @Test
    public void should_display_registered_BUY_and_SELL_orders_summary_as_per_type_and_price() {
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 3.5, 307);
        liveOrderBoard.registerOrder("Rob", Order.Type.SELL, 3.5, 306);
        liveOrderBoard.registerOrder("Test1", Order.Type.BUY, 3.5, 306);
        liveOrderBoard.registerOrder("John", Order.Type.BUY, 2.9, 306);
        liveOrderBoard.registerOrder("Rob", Order.Type.SELL, 1, 309);
        liveOrderBoard.registerOrder("Alice", Order.Type.SELL, 2.0, 306);
        List<OrderSummary> orderSummaries = liveOrderBoard.summary();

        assertEquals(4, orderSummaries.size());

        //first BUY orderSummary
        assertEquals(new Double(3.5), orderSummaries.get(0).getQuantity().getMass());
        assertEquals(new Double(307), new Double(orderSummaries.get(0).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

        //second BUY orderSummary
        assertEquals(new Double(6.4), orderSummaries.get(1).getQuantity().getMass());
        assertEquals(new Double(306), new Double(orderSummaries.get(1).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

        //third SELL orderSummary
        assertEquals(new Double(5.5), orderSummaries.get(2).getQuantity().getMass());
        assertEquals(new Double(306), new Double(orderSummaries.get(2).getPricePerQuantityType().getAmount().getAmount().doubleValue()));

        //fourth SELL orderSummary
        assertEquals(new Double(1), orderSummaries.get(3).getQuantity().getMass());
        assertEquals(new Double(309), new Double(orderSummaries.get(3).getPricePerQuantityType().getAmount().getAmount().doubleValue()));
    }
}
