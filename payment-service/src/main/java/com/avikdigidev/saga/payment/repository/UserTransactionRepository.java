package com.avikdigidev.saga.payment.repository;


import com.avikdigidev.saga.payment.entities.UserBalance;
import com.avikdigidev.saga.payment.entities.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
