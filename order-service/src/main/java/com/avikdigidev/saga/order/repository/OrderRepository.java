package com.avikdigidev.saga.order.repository;

import com.avikdigidev.saga.order.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
