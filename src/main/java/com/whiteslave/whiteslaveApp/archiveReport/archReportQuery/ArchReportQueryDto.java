package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArchReportQueryDto implements Serializable {

    private Long id;
    private LocalDateTime requestDate;
    private LocalDate reportDate;
    private String pdfFileName;
    private SearchResult searchResult;
    private ReportType reportType;
    private String requestNip;
    private String requestRegon;
    private String requestBankAccount;

}
