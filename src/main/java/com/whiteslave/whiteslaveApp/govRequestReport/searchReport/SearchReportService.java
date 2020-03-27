package com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain;

import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.BankAccount;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.DateParam;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.NipNumber;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.RegonNumber;
import org.springframework.validation.annotation.Validated;

@Validated
public interface SearchReportService {

    SearchReportDto searchByBankAccountAndDate(@BankAccount String bankAccount, @DateParam String date);

    SearchReportDto searchByBankAccountsAndDate(@BankAccount String bankAccounts, @DateParam String date);

    SearchReportDto searchByNipAndDate(@NipNumber String nip, @DateParam String date);

    SearchReportDto searchByNipsAndDate(@NipNumber String nips, @DateParam String date);

    SearchReportDto searchByRegonAndDate(@RegonNumber String regon, @DateParam String date);

    SearchReportDto searchByRegonsAndDate(@RegonNumber String regons, @DateParam String date);
}
