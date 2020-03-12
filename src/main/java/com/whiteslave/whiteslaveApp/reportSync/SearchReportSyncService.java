package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.BankAccount;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.DateParam;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.NipNumber;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.RegonNumber;

public interface SearchReportSyncService {

    SearchReportDto searchAndSynchronizeByBankAccountAndDate(String bankAccount,String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDateByBankAccountsAndDate(String bankAccounts,String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDateByNipAndDate(String nip,String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDateByNipsAndDate(String nips, String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDateByRegonAndDate(String regon,String date);

    SearchReportDto searchAndSynchronizeByBankAccountAndDateByRegonsAndDate(String regons,String date);

}
