package com.whiteslave.whiteslaveApp.searchReport.domain;

import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.validator.BankAccount;
import com.whiteslave.whiteslaveApp.validator.NipNumber;
import com.whiteslave.whiteslaveApp.validator.RegonNumber;
import org.springframework.validation.annotation.Validated;

import javax.validation.ValidationException;

@Validated
public interface SearchReportService {

    SearchReportDto searchByBankAccountAndDate(@BankAccount String bankAccount, String date);

    SearchReportDto searchByBankAccountsAndDate(@BankAccount String bankAccounts, String date);

    SearchReportDto searchByNipAndDate(@NipNumber String nip, String date) throws ValidationException;

    SearchReportDto searchByNipsAndDate(@NipNumber String nips, String date);

    SearchReportDto searchByRegonAndDate(@RegonNumber String regon, String date);

    SearchReportDto searchByRegonsAndDate(@RegonNumber String regons, String date);
}
