package com.avikdigidev.saga.order.service;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import com.avikdigidev.saga.commons.event.OrderStatus;
import com.avikdigidev.saga.order.entities.PurchaseOrder;
import com.avikdigidev.saga.order.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusPublisher orderStatusPublisher;
@Transactional
    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO) {
        PurchaseOrder order = orderRepository.save(convertDTOToEntity(orderRequestDTO));
        orderRequestDTO.setOrderId(order.getId());
        //produce kafka event with order created status
        orderStatusPublisher.publishOrderEvent(orderRequestDTO,OrderStatus.ORDER_CREATED);
        return order;
    }
    public List<PurchaseOrder> getAllPurchases(){
    return orderRepository.findAll();
    }

    private PurchaseOrder convertDTOToEntity(OrderRequestDTO orderRequestDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        BeanUtils.copyProperties(orderRequestDTO, purchaseOrder);
        purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
        return purchaseOrder;
    }
}
