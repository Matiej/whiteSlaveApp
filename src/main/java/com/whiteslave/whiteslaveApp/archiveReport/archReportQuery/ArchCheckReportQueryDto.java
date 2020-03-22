package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArchCheckReportQueryDto implements Serializable {

    private Long id;
    private String requestId;
    private String accountAssigned;
    private LocalDateTime requestDate;
    private LocalDate reportDate;
    private String pdfFileName;
    private SearchResult searchResult;
    private ReportType reportType;
    private String requestNip;
    private String requestRegon;
    private String requestBankAccount;
}
