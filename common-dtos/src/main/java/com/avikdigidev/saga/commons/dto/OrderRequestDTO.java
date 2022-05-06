package com.avikdigidev.saga.commons.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private Integer  amount;
    private Integer orderId;
}
