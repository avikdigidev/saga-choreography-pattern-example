package com.avikdigidev.saga.order.config;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import com.avikdigidev.saga.commons.event.OrderStatus;
import com.avikdigidev.saga.commons.event.PaymentStatus;
import com.avikdigidev.saga.order.entities.PurchaseOrder;
import com.avikdigidev.saga.order.repository.OrderRepository;
import com.avikdigidev.saga.order.service.OrderStatusPublisher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository orderRepository;
@Autowired
    private OrderStatusPublisher orderStatusPublisher;

public void updateOrder(int orderId, Consumer<PurchaseOrder> consumer){
orderRepository.findById(orderId).ifPresent(consumer.andThen(this::updateOrder));
}
@Transactional
    private void updateOrder(PurchaseOrder purchaseOrder) {
    boolean isCompleted = purchaseOrder.getPaymentStatus().equals(PaymentStatus.COMPLETED);
      OrderStatus orderStatus= (isCompleted)?OrderStatus.ORDER_COMPLETED: OrderStatus.ORDER_CANCELLED;
      purchaseOrder.setOrderStatus(orderStatus);
if (!isCompleted){
    orderStatusPublisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
}
    }

    private OrderRequestDTO convertEntityToDto(PurchaseOrder purchaseOrder) {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        BeanUtils.copyProperties(purchaseOrder,orderRequestDTO);

        return orderRequestDTO;
    }
}
