package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import org.hibernate.HibernateException;

import java.io.File;

public interface ArchReportService {

    File generateReportPdf(ReportSyncRequest reportSyncRequest);

    ReportSyncRequest saveReport(ReportSyncRequest reportSyncRequest) throws HibernateException;
}
