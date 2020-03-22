package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PdfConfig {

    @Bean
    public PdfReportService pdfReportService() {
        return new PdfReportService(new PdfTableService());
    }


}
