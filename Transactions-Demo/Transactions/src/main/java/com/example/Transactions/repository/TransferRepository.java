package com.example.Transactions.repository;

import com.example.Transactions.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<TransferRecord,Long> {

}
