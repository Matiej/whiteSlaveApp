package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PdfReportPackageTestConfig {

    @Bean
    public PdfReportService pdfReportService() {
        return new PdfReportService(new PdfTableService());
    }

}
