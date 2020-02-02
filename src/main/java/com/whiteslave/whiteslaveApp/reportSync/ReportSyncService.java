package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;

public interface ReportSyncService {

   void syncAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams);
   void syncAndSaveSearchReport(SearchReportDto searchReportDto, String ... requestParams);
}
