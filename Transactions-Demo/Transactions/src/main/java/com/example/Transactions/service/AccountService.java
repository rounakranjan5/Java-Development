package com.example.Transactions.service;

import com.example.Transactions.entity.Account;
import com.example.Transactions.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
@Setter
public class AccountService {

    AccountRepository accountRepository;

    public void createAccount(Account account){
        accountRepository.save(account);
    }

}
