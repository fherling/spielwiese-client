package de.frankherling.spielwiese.client.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

    private final OrderService orderService;

    @Scheduled(fixedDelayString = "2000")
    public void scheduleCreateOrder() {
        log.info("OrderCreation scheduled");
        orderService.createOrder();
    }

    @Scheduled(fixedDelayString = "3000")
    public void scheduleGetOrder() {
        log.info("Order scheduled");
        orderService.getOrders();
    }
}
