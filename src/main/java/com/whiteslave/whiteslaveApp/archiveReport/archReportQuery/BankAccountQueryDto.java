package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountQueryDto {
    private Long id;
    private String bankAccount;
}
