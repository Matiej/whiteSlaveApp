package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CheckReportQueryDto implements Serializable {

    private Long id;
    private LocalDateTime requestDate;
    private LocalDate reportDate;
    private String pdfFileName;
    private SearchResult searchResult;
    private ReportType reportType;
    private String requestNip;
    private String requestRegon;
    private String requestBankAccount;
    private String requestId;
    private String accountAssigned;


    public CheckReportQueryDto(String requestId, String accountAssigned) {
        this.requestId = requestId;
        this.accountAssigned = accountAssigned;
    }

    public CheckReportQueryDto(Long id, String requestId, String accountAssigned, LocalDateTime requestDate,
                               LocalDate reportDate, String pdfFileName, SearchResult searchResult,
                               ReportType reportType, String requestNip, String requestRegon, String requestBankAccount) {
        this.id = id;
        this.requestDate = requestDate;
        this.reportDate = reportDate;
        this.pdfFileName = pdfFileName;
        this.searchResult = searchResult;
        this.reportType = reportType;
        this.requestNip = requestNip;
        this.requestRegon = requestRegon;
        this.requestBankAccount = requestBankAccount;
        this.requestId = requestId;
        this.accountAssigned = accountAssigned;
    }
}
