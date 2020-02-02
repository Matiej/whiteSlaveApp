package com.whiteslave.whiteslaveApp.govRequestReport.checkReport;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.BankAccount;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.DateParam;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.NipNumber;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.RegonNumber;
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
