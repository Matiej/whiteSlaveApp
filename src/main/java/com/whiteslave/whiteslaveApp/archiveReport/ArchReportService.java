package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import org.hibernate.HibernateException;

public interface ArchReportService {

    void generateReportPdf(ReportSyncRequest reportSyncRequest);

    ReportSyncRequest saveReport(ReportSyncRequest reportSyncRequest) throws HibernateException;
}
