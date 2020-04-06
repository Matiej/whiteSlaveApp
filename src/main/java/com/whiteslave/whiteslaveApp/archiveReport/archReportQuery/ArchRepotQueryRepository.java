package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.entity.ReportSyncRequestEntity;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchRepotQueryRepository extends JpaRepository<ReportSyncRequestEntity, Long> {

    @Query("select new com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.CheckReportQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = 'CHECK'")
    List<CheckReportQueryDto> findAllCheckReports();

    @Query("select new com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.CheckReportQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = 'CHECK' AND r.id = :id")
    CheckReportQueryDto findCheckReportById(Long id);

    List<ReportSyncRequestEntity> findAllBySearchResult(SearchResult searchResult);

}
