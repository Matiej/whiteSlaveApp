package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.CheckReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.BankAccount;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.DateParam;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.NipNumber;
import com.whiteslave.whiteslaveApp.govRequestReport.validator.RegonNumber;
import org.springframework.stereotype.Service;

@Service
public interface CheckReportSyncService {

    CheckReportDto checkAndSynchronizeByNipAndBankAccoutAndDate(String nip, String bankAccount, String date);
    CheckReportDto checkAmdSynchronizeByRegonAndBankAccoutnAndDate(String regon, String bankAccount,String date);
}
