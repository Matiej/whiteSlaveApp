package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SearchNegativeReportQueryView extends SearchPositiveReportQueryView {

    @Override
    @Value("#{target.responseReportEntity.requestId}")
    String getRequestId();

    @Override
    Long getId();

    @Override
    LocalDateTime getRequestDate();

    @Override
    LocalDate getReportDate();

    @Override
    String getPdfFileName();

    @Override
    String getSearchResult();

    @Override
    String getRequestNip();

    @Override
    String getRequestRegon();

    @Override
    String getRequestBankAccount();

    @Override
    @Value("#{target.responseReportEntity.id}")
    Long getSyncRequestEntityId();

    @Override
    @Value("#{target.responseReportEntity.subjectNo}")
    String getSubjectNo();

    @Override
    @Value("#{''}")
    String getName();

    @Override
    @Value("#{''}")
    String getStatusVat();
}
