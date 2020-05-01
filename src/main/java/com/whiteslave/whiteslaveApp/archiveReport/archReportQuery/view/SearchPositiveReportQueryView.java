package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SearchPositiveReportQueryView extends ArchReportQueryView {

    @Override
    @Value("#{target.govSearchResponseEntity.requestId}")
    String getRequestId();

    @Override
    @Value("#{target.id}")
//    @Value("#{target.govResponseEntity.id}")
    Long getId();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.requestDate}")
    LocalDateTime getRequestDate();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.reportDate}")
    LocalDate getReportDate();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.pdfFileName}")
    String getPdfFileName();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.searchResult}")
    String getSearchResult();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.requestNip}")
    String getRequestNip();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.requestRegon}")
    String getRequestRegon();

    @Override
    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.requestBankAccount}")
    String getRequestBankAccount();

    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.id}")
    Long getSyncRequestEntityId();

    @Value("#{target.govSearchResponseEntity.subjectNo}")
    String getSubjectNo();

    String getName();

    String getStatusVat();
}
