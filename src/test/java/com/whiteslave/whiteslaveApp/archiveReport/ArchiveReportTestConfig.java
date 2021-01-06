package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.archiveReport.pdfReport.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArchiveReportTestConfig {

//    @Autowired
//    private  PdfReportService pdfReportService;


    @Bean
    public ReportSyncRequestRepository reportSyncRequestRepository() {
        return new ReportSyncRequestRepositoryTest();
    }

    @Bean
    public ReportSyncRequest2EntityConverter reportSyncRequest2EntityConverter() {
        return new ReportSyncRequest2EntityConverter();
    }

    @Bean
    public ArchReportService archReportService(PdfReportService pdfReportService,ReportSyncRequest2EntityConverter converter, ReportSyncRequestRepository reportSyncRequestRepository) {
        return new ArchReportServiceImpl(converter, pdfReportService, reportSyncRequestRepository);
    }
}
