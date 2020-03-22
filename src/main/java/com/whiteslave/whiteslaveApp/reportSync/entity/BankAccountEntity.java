package com.whiteslave.whiteslaveApp.reportSync.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BANK_ACCOUNT")
@Builder
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BK_ID")
    private long id;

    @Column(name = "BK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    public BankAccountEntity(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}

