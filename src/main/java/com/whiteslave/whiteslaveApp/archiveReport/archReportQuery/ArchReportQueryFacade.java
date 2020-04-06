package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import java.util.List;

public interface ArchReportQueryFacade {

    List<SearchReportSubjectQueryDto> findBy();

    List<CheckReportQueryDto> findAllCheckReports();

    CheckReportQueryDto findCheckReportById(Long id);
}
