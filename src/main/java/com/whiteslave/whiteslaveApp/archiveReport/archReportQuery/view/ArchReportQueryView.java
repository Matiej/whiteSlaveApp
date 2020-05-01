package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ArchReportQueryView {

    String getRequestId();
    Long getId();
    LocalDateTime getRequestDate();
    LocalDate getReportDate();
    String getPdfFileName();
    String getSearchResult();
    String getRequestNip();
    String getRequestRegon();
    String getRequestBankAccount();
}
