package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SearchPositiveReportQueryView extends ArchReportQueryView {

    @Override
    @Value("#{target.searchResponseReportEntity.requestId}")
    String getRequestId();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.id}")
    Long getId();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestDate}")
    LocalDateTime getRequestDate();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.reportDate}")
    LocalDate getReportDate();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.pdfFileName}")
    String getPdfFileName();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.searchResult}")
    String getSearchResult();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestNip}")
    String getRequestNip();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestRegon}")
    String getRequestRegon();

    @Override
    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestBankAccount}")
    String getRequestBankAccount();

    @Value("#{target.id}")
    Long getSubjectId();

    @Value("#{target.searchResponseReportEntity.subjectNo}")
    String getSubjectNo();

    String getName();

    String getStatusVat();
}
