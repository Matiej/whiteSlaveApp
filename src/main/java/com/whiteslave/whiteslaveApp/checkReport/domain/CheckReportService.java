package com.whiteslave.whiteslaveApp.checkReport.domain;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;

public interface CheckReportService {

    CheckReportDto checkByNipAndBankAccoutAndDate(String nip, String bankAccount, String date);

    CheckReportDto checkByRegonAndBankAccoutnAndDate(String regon, String bankAccount, String date);
}
