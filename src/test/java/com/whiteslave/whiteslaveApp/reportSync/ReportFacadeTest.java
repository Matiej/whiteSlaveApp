package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {  ReportSyncTestConfig.class})
class ReportFacadeTest {

    @Test
    void searchAndSynchronizeByBankAccountAndDate(@Autowired ReportFacade reportFacade) {
        String bankAccount = "123";
        String date = "2020-01-01";
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByBankAccountAndDate(bankAccount, date);
        System.out.println(searchReportDto);

    }
}