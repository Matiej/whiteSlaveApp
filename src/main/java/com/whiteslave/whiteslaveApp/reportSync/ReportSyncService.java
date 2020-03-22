package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
class ReportSyncService {

    private final GovReportDto2ReportSyncRequestConverter govReportDto2ReportSyncRequestConverter;
    private final ArchReportService archReportService;

    public void syncToPDFAndSaveCheckReport(CheckReportDto checkReportDto, String... requestParams) {

        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.checkReportDtoConvertToReportSyncConverter(checkReportDto, requestParams);
        File file = archReportService.generateReportPdf(reportSyncRequest);
        archReportService.saveReport(reportSyncRequest);
    }

    public void syncToPDFAndSaveSearchReport(SearchReportDto searchReportDto, String... requestParams) throws HibernateException {
        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.searchReportDtoConvertToReportSyncConverter(searchReportDto, requestParams);
        File file = archReportService.generateReportPdf(reportSyncRequest);
        archReportService.saveReport(reportSyncRequest);
    }

}
