package com.example.Transactions.controller;

import com.example.Transactions.entity.Account;
import com.example.Transactions.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acc")
@AllArgsConstructor
public class AccountController {

    AccountService accountService;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account account){
        accountService.createAccount(account);
        return ResponseEntity.ok("done");
    }

}
