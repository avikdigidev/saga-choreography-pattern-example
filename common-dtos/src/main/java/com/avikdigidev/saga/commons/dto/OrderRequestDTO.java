package com.avikdigidev.saga.commons.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private Integer userID;
    private Integer productID;
    private Integer  amount;
    private Integer orderID;
}
