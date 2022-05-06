package com.avikdigidev.saga.order.config;

import com.avikdigidev.saga.commons.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderConsumerConfig {
    @Autowired
    private OrderStatusUpdateHandler orderStatusUpdateHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer() {
        //listen payment event topic
        //check payment status
        //if completed then complete the order
        //if failed then cancel the order
        return payment -> orderStatusUpdateHandler.updateOrder(payment.getPaymentRequestDTO().getOrderID(),
                purchaseOrder -> purchaseOrder.setPaymentStatus(payment.getPaymentStatus()));
    }
}
