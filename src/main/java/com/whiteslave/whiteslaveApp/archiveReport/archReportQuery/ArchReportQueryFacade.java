package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.CheckReportQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchReportDetailsQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.entity.ReportSyncRequestEntity;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ArchReportQueryFacade {

    List<CheckReportQueryView> allCheckReports();

    CheckReportQueryDto findCheckReportById(Long id);

    List<SearchPositiveReportQueryView> allSearchReports();

    SearchReportDetailsQueryView findSearchReportDetailsById(Long id);

    Resource findReportFileByEntityId(Long id);


}
