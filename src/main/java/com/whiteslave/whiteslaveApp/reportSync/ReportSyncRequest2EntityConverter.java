package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.GovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.entity.CheckResponseReportEntity;
import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import org.springframework.stereotype.Component;

@Component
class ReportSyncRequest2EntityConverter {

    public ReportSyncRequestEntity convert2Entity(ReportSyncRequest reportSyncRequest) {
        ReportSyncRequestEntity entity = new ReportSyncRequestEntity();
        entity.setRequestDate(reportSyncRequest.getRequestDate());
        entity.setReportDate(reportSyncRequest.getReportDate());
        entity.setPdfFileName(reportSyncRequest.getPdfFileName());
        entity.setSearchResult(reportSyncRequest.getSearchResult());
        entity.setReportType(reportSyncRequest.getReportType());
        entity.setRequestNip(reportSyncRequest.getRequestNip());
        entity.setRequestRegon(reportSyncRequest.getRequestRegon());
        entity.setRequestBankAccount(reportSyncRequest.getRequestBankAccount());
        GovResponseReportSync govResponseReportSync = reportSyncRequest.getGovResponseReportSync();
        if(govResponseReportSync instanceof CheckGovResponseReportSync) {
            entity.setResponseReportEntity(covertCheckGovReportSync2ReportEntity((CheckGovResponseReportSync) govResponseReportSync));
        }
        return entity;
    }

    public CheckResponseReportEntity covertCheckGovReportSync2ReportEntity(CheckGovResponseReportSync checkGovReportSync) {
        CheckResponseReportEntity entity = new CheckResponseReportEntity();
        entity.setRequestId(checkGovReportSync.getRequestId());
        entity.setAccountAssigned(checkGovReportSync.getAccountAssigned());
        return entity;
    }
}
