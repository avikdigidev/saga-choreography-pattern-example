package com.avikdigidev.saga.payment.entities;

import com.avikdigidev.saga.commons.event.OrderStatus;
import com.avikdigidev.saga.commons.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PURCHASE_ORDER")
public class UserBalance {
    @Id
    @GeneratedValue
    private Integer userId;
    private Integer amount;


}
