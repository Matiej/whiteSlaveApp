package com.whiteslave.whiteslaveApp.reportSync.domain;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSyncRequest {

    private LocalDateTime requestDate;
    private LocalDate reportDate;
    private String pdfFileName;
    private SearchResult searchResult;
    private ReportType reportType;
    private GovResponseReportSync govResponseReportSync;
    private String requestNip;
    private String requestRegon;
    private String requestBankAccount;

}

