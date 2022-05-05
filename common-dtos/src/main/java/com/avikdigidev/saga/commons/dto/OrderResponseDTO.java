package com.avikdigidev.saga.commons.dto;

import com.avikdigidev.saga.commons.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {


    private Integer userID;
    private Integer productID;
    private Integer amount;
    private Integer orderID;
    private OrderStatus orderStatus;
}
