package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;

public interface ReportFacade {

    CheckReportDto checkAndSynchronizeByNipAndBankAccoutAndDate(String nip, String bankAccount, String date);
    CheckReportDto checkAmdSynchronizeByRegonAndBankAccoutnAndDate(String regon, String bankAccount,String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDate(String bankAccount, String date);
    SearchReportDto searchAndSynchronizeByBankAccountsAndDate(String bankAccounts, String date);
    SearchReportDto searchAndSynchronizeByNipAndDate(String nip, String date);
    SearchReportDto searchAndSynchronizeByNipsAndDate(String nips, String date);
    SearchReportDto searchAndSynchronizeByRegonAndDate(String regon, String date);
    SearchReportDto searchAndSynchronizeByRegonsAndDate(String regons, String date);
}
