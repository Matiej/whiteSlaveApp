package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;

import java.util.Map;

public interface ReportSyncService {

   void synAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams);
}
