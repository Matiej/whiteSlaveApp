package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportSyncTestConfig {

    @Autowired
    ArchReportService archReportService;


    @Bean
    GovReportDto2ReportSyncRequestConverter govReportDto2ReportSyncRequestConverter() {
        return new GovReportDto2ReportSyncRequestConverter(new CheckReportDto2CheckGovResponseConverter(),
                new SearchReportDto2SearchGovResponseConverter());
    }


    ReportSyncService reportSyncService() {
        return new ReportSyncService(govReportDto2ReportSyncRequestConverter(), archReportService);
    }



}
