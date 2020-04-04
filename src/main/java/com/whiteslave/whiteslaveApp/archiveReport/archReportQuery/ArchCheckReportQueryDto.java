package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ArchCheckReportQueryDto extends ArchReportQueryDto {

    private String requestId;
    private String accountAssigned;


    public ArchCheckReportQueryDto(String requestId, String accountAssigned) {
        this.requestId = requestId;
        this.accountAssigned = accountAssigned;
    }

    public ArchCheckReportQueryDto(Long id, String requestId, String accountAssigned, LocalDateTime requestDate,
                                   LocalDate reportDate, String pdfFileName, SearchResult searchResult,
                                   ReportType reportType, String requestNip, String requestRegon, String requestBankAccount) {
        super(id, requestDate, reportDate, pdfFileName, searchResult, reportType, requestNip, requestRegon, requestBankAccount);
        this.requestId = requestId;
        this.accountAssigned = accountAssigned;
    }
}
