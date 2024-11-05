package com.example.session7;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PaymentService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void processPayment(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();

        fromAccount.debit(amount);
        toAccount.credit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}


