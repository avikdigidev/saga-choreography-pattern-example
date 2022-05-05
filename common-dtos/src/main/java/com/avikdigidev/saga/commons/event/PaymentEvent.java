package com.avikdigidev.saga.commons.event;

import com.avikdigidev.saga.commons.dto.PaymentRequestDTO;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
public class PaymentEvent implements Event{
    private UUID eventId= UUID.randomUUID();
    private Date date = new Date();
    private PaymentRequestDTO paymentRequestDTO;
    private PaymentStatus paymentStatus;
    @Override
    public UUID getEventID() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public PaymentEvent(PaymentRequestDTO paymentRequestDTO, PaymentStatus paymentStatus) {
        this.paymentRequestDTO = paymentRequestDTO;
        this.paymentStatus = paymentStatus;
    }
}
