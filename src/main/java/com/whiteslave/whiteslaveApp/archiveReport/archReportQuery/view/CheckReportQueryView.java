package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import org.springframework.beans.factory.annotation.Value;

public interface CheckReportQueryView extends ArchReportQueryView {

    @Override
    @Value("#{target.responseReportEntity.requestId}")
    String getRequestId();

    @Value("#{target.responseReportEntity.accountAssigned}")
    String getAccountAssigned();
}
