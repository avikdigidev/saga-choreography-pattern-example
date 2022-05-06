package com.avikdigidev.saga.payment.repository;


import com.avikdigidev.saga.payment.entities.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
