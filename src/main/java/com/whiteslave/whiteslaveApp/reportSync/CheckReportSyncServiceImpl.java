package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.CheckReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class CheckReportSyncServiceImpl implements CheckReportSyncService{

    private final CheckReportService checkReportService;
    private final ArchReportService archReportService;

    @Override
    public CheckReportDto checkAndSynchronizeByNipAndBankAccoutAndDate(String nip, String bankAccount, String date) {
        return null;
    }

    @Override
    public CheckReportDto checkAmdSynchronizeByRegonAndBankAccoutnAndDate(String regon, String bankAccount, String date) {
        return null;
    }
}
