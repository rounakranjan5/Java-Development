package com.example.Transactions.service;

import com.example.Transactions.entity.Account;
import com.example.Transactions.entity.TransferRecord;
import com.example.Transactions.repository.AccountRepository;
import com.example.Transactions.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Getter
@Setter
public class TransferService {

    AccountRepository accountRepository;
    TransferRepository transferRepository;

    @Transactional
    public void transfer(Long fromAccId,Long toAccId,Long amount){

        Account fromAccount=accountRepository.findById(fromAccId).orElseThrow(() -> new RuntimeException("From Account Not Found"));
        Account toAccount=accountRepository.findById(toAccId).orElseThrow(()-> new RuntimeException("To Account Not found"));

        fromAccount.debitAccount(amount);

//        accountRepository.saveAndFlush(fromAccount);

        toAccount.creditAccount(amount);
//        accountRepository.saveAndFlush(toAccount);

        transferRepository.save(new TransferRecord(toAccId,fromAccId,amount));
//        transferRepository.flush();

        throw new RuntimeException("some error occured ...");

    }

}
