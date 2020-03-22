package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchRepotQueryRepository extends JpaRepository<ReportSyncRequestEntity, Long> {

    @Query("select new com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.ArchCheckReportQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = :reportType")
    List<ArchCheckReportQueryDto> findAllCheckReports(ReportType reportType);

    @Query("select new com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.ArchCheckReportQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = :reportType AND r.id = :id")
    ArchCheckReportQueryDto findCheckReportById(ReportType reportType, Long id);
}
