package de.frankherling.spielwiese.client.application.service;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.client.OrdersApi;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import de.frankherling.spielwiese.client.application.port.OrderPort;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements OrderPort {

    private final OrdersApi ordersApi;

    @Override
    @Timed
    @Counted
    public void createOrder() {
        log.info("Order created");
        try {
            UUID orderId = UUID.randomUUID();
            MDC.put("X-ORDERNUMBER", orderId.toString());

            Order order = Order.builder()
                    .orderId(orderId)
                    .orderStatus(Order.OrderStatusEnum.PLACED)
                    .orderType(Order.OrderTypeEnum.FREE)
                    .build();
            Order order1 = ordersApi.createOrder(order);
            log.info("Order created: {}", order1);


        } finally {
            MDC.clear();
        }
    }

    @Timed
    @Counted
    public void getOrders() {
        log.info("retrieve orders");
        List<Order> orders = ordersApi.getOrders();
        orders.forEach(order -> log.info("Order: {}", order));
    }


}
