package com.avikdigidev.saga.order.service;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import com.avikdigidev.saga.commons.event.OrderEvent;
import com.avikdigidev.saga.commons.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {
    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDTO,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
