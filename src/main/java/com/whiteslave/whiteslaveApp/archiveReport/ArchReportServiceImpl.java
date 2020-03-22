package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.archiveReport.pdfReport.PdfReportService;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
@AllArgsConstructor
class ArchReportServiceImpl implements ArchReportService {

    private final ReportSyncRequest2EntityConverter converter;
    private final PdfReportService pdfReportService;
    private final ReportSyncRequestRepository repository;

    @Override
    public File generateReportPdf(ReportSyncRequest reportSyncRequest) {
        return pdfReportService.preparePdfReport(reportSyncRequest);
    }

    @Override
    public ReportSyncRequest saveReport(ReportSyncRequest reportSyncRequest) throws HibernateException {
        try {
            ReportSyncRequestEntity reportSyncRequestEntity = converter.convert2Entity(reportSyncRequest);
            ReportSyncRequestEntity savedReportSyncRequestEntity = repository.save(reportSyncRequestEntity);
            String correctInfoMessage = String.format("Report saved correct. ID: %d, type: %s, gov request ID: %s", savedReportSyncRequestEntity.getId(),
                    savedReportSyncRequestEntity.getReportType().name(), savedReportSyncRequestEntity.getResponseReportEntity().getRequestId());
            log.info(correctInfoMessage);
            return reportSyncRequest;
        } catch (HibernateException e) {
            throw new RuntimeException("Data Base server error. Can not get or save any report. ", e);
        }

    }
}
