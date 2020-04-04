package com.whiteslave.whiteslaveApp.archiveReport.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BANK_ACCOUNT")
public class BankAccountEntity implements Serializable {

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
