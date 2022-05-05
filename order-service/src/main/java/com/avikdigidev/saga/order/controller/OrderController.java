package com.avikdigidev.saga.order.controller;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import com.avikdigidev.saga.order.entities.PurchaseOrder;
import com.avikdigidev.saga.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
@PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
return orderService.createOrder(orderRequestDTO);
    }
@GetMapping("/orders")
    public List<PurchaseOrder> getAllPurchases(){
        return orderService.getAllPurchases();
    }
}
