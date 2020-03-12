package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import org.hibernate.HibernateException;

public interface ReportSyncService {

   void syncToPDFAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams) throws HibernateException;
   void syncToPDFAndSaveSearchReport(SearchReportDto searchReportDto, String ... requestParams) throws HibernateException;
}
