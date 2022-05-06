package com.avikdigidev.saga.payment.service;

import com.avikdigidev.saga.commons.dto.OrderRequestDTO;
import com.avikdigidev.saga.commons.dto.PaymentRequestDTO;
import com.avikdigidev.saga.commons.event.OrderEvent;
import com.avikdigidev.saga.commons.event.PaymentEvent;
import com.avikdigidev.saga.commons.event.PaymentStatus;
import com.avikdigidev.saga.payment.entities.UserBalance;
import com.avikdigidev.saga.payment.entities.UserTransaction;
import com.avikdigidev.saga.payment.repository.UserBalanceRepository;
import com.avikdigidev.saga.payment.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {
    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalance() {
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101, 100300),
                new UserBalance(102, 10000),
                new UserBalance(103, 1000),
                new UserBalance(104, 9)).collect(Collectors.toList()));
    }


    /*    //get the user id
            //check the balance available
            //if balance is sufficient then payment completed and deduct amount
            // else payment fails cancel the order and update the amount in db
            */
    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequestDTO orderRequestDTO = orderEvent.getOrderRequestDTO();
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(orderRequestDTO.getOrderID(), orderRequestDTO.getUserID(), orderRequestDTO.getAmount());

        return userBalanceRepository.findById(orderRequestDTO.getUserID())
                .filter(ub -> ub.getAmount() > orderRequestDTO.getAmount())
                .map(ub -> {
                    ub.setAmount(ub.getAmount() - orderRequestDTO.getAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDTO.getOrderID(), orderRequestDTO.getUserID(), orderRequestDTO.getAmount()));
                    return new PaymentEvent(paymentRequestDTO, PaymentStatus.COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDTO, PaymentStatus.FAILED));


    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequestDTO().getOrderID()).ifPresent(ut -> {
            userTransactionRepository.delete(ut);
            userTransactionRepository.findById(ut.getOrderId())
                    .ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));
        });
    }
}
