package com.avikdigidev.saga.commons.event;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@Data
public class OrderEvent implements Event{
    private UUID eventId= UUID.randomUUID();
    private Date date = new Date();
    private OrderRequestDTO orderRequestDTO;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventID() {
        return this.eventId;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    public OrderEvent(OrderRequestDTO orderRequestDTO, OrderStatus orderStatus) {
        this.orderRequestDTO = orderRequestDTO;
        this.orderStatus = orderStatus;
    }
}
