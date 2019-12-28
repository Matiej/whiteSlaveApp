package com.whiteslave.whiteslaveApp.checkReport.domain;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.validator.BankAccount;
import com.whiteslave.whiteslaveApp.validator.DateParam;
import com.whiteslave.whiteslaveApp.validator.NipNumber;
import com.whiteslave.whiteslaveApp.validator.RegonNumber;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CheckReportService {

    CheckReportDto checkByNipAndBankAccoutAndDate(@NipNumber String nip,
                                                  @BankAccount String bankAccount,
                                                  @DateParam String date);

    CheckReportDto checkByRegonAndBankAccoutnAndDate(@RegonNumber String regon,
                                                     @BankAccount String bankAccount,
                                                     @DateParam String date);
}
