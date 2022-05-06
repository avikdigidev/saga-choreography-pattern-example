package com.avikdigidev.saga.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    private Integer orderId;
    private Integer userID;
    private Integer amount;
}
