package com.avikdigidev.saga.payment.config;

import com.avikdigidev.saga.commons.event.OrderEvent;
import com.avikdigidev.saga.commons.event.OrderStatus;
import com.avikdigidev.saga.commons.event.PaymentEvent;
import com.avikdigidev.saga.payment.service.PaymentService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {
    @Autowired
    private PaymentService paymentService;
@Bean //this will act as both publisher and consumer
    public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){
return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
    //get the user id
        //check the balance available
        //if balance is sufficient then payment completed and deduct amount
        // else payment fails cancel the order and update the amount in db
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent) );
        }else{
            return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent) );
        }
    }
}
