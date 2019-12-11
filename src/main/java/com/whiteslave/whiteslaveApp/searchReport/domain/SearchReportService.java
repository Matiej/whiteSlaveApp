package com.whiteslave.whiteslaveApp.searchReport.domain;

import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;

public interface SearchReportService {

    SearchReportDto searchByBankAccountAndDate(String bankAccount, String date);
}
