package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.CheckReportQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView;

import java.util.List;

public interface ArchReportQueryFacade {

    List<SearchPositiveReportQueryView> allSearchReports();

    List<CheckReportQueryDto> findAllCheckReports();

    CheckReportQueryDto findCheckReportById(Long id);

    List<CheckReportQueryView> allCheckReports();
}
