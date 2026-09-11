package com.example.Transactions_Part_2.service;

import com.example.Transactions_Part_2.entity.PaymentAudit;
import com.example.Transactions_Part_2.repository.PaymentAuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional

@Service
@AllArgsConstructor
public class PaymentAuditService {

    PaymentAuditRepository paymentAuditRepository;

    // Propagation and isolation level

    @Transactional(
            propagation=Propagation.REQUIRED,
            isolation= Isolation.REPEATABLE_READ
    )
    public void audit(Integer amount, Long id, String productName) {
        PaymentAudit paymentAudit=new PaymentAudit(id,amount,true);
        paymentAuditRepository.save(paymentAudit);
    }

}
