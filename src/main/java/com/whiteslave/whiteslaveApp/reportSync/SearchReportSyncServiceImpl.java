package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.SearchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SearchReportSyncServiceImpl implements SearchReportSyncService{

    private final SearchReportService searchReportService;
    private final ArchReportService archReportService;

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDate(String bankAccount, String date) {
        return null;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDateByBankAccountsAndDate(String bankAccounts, String date) {
        return null;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDateByNipAndDate(String nip, String date) {
        return null;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDateByNipsAndDate(String nips, String date) {
        return null;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDateByRegonAndDate(String regon, String date) {
        return null;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDateByRegonsAndDate(String regons, String date) {
        return null;
    }
}
