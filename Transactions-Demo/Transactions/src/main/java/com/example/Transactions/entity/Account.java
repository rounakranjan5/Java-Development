package com.example.Transactions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long balance;
    private String name;

    public void debitAccount(Long debitamount){
        if(debitamount<=0 || debitamount==null ) throw new RuntimeException("Amount should be greater than 0");

        if((balance-debitamount)<0) throw new RuntimeException("Insufficient Fund !!");

        balance-=debitamount;
    }

    public void creditAccount(Long creditamount){
        if(creditamount<=0 || creditamount==null ) throw new RuntimeException("Amount should be greater than 0");


        balance+=creditamount;
    }

}
