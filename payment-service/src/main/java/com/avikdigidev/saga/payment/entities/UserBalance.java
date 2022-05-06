package com.avikdigidev.saga.payment.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalance {
    @Id
    private Integer userId;
    private Integer amount;


}
