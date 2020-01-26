package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;

public interface ReportSyncService {

   void syncAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams);
   void syncAndSaveSearchReport(SearchReportDto searchReportDto, String ... requestParams);
}
