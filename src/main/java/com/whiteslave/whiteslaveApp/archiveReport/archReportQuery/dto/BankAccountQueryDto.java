package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountQueryDto implements Serializable {
    private Long id;
    private String bankAccount;
}
