package com.example.Transactions.controller;

import com.example.Transactions.entity.TransferRecord;
import com.example.Transactions.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@AllArgsConstructor
public class TransferController {

    TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferRecord transferRecord){

        transferService.transfer(transferRecord.getFromAccountId(),transferRecord.getToAccountId(),transferRecord.getAmount());

        return ResponseEntity.ok("Transfer successfull");
    }

}
