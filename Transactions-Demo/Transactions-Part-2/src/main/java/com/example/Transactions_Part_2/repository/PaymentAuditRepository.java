package com.example.Transactions_Part_2.repository;

import com.example.Transactions_Part_2.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAuditRepository extends JpaRepository<PaymentAudit,Long> {

}
