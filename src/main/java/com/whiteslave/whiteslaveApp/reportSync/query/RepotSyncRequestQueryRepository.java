package com.whiteslave.whiteslaveApp.reportSync.query;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepotSyncRequestQueryRepository extends JpaRepository<ReportSyncRequestEntity, Long> {

    @Query("select new com.whiteslave.whiteslaveApp.reportSync.query.ReportSyncRequestQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = :reportType")
    List<ReportSyncRequestQueryDto> findAllCheckReports(ReportType reportType);

    @Query("select new com.whiteslave.whiteslaveApp.reportSync.query.ReportSyncRequestQueryDto(r.id, " +
            "r.responseReportEntity.requestId, r.responseReportEntity.accountAssigned, r.requestDate, r.reportDate, " +
            "r.pdfFileName, r.searchResult, r.reportType , r.requestNip, r.requestRegon, r.requestBankAccount) " +
            "FROM ReportSyncRequestEntity r WHERE r.reportType = :reportType AND r.id = :id")
    ReportSyncRequestQueryDto findCheckReportById(ReportType reportType, Long id);
}
