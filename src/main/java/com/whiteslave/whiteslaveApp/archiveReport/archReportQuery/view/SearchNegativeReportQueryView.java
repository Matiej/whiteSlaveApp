package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SearchNegativeReportQueryView extends SearchPositiveReportQueryView {

    @Override
    @Value("#{target.govResponseEntity.requestId}")
    String getRequestId();

    //hardcode id value 0, just to identifi negative reports in angular front end
    //negative values are not saved as SubjectEntity.
    //SubjectEntity is source for positive queries for searchRerpots.
    //no id for negatives in subjectEntity. Meaby next time find better solution
    @Override
    @Value("#{0}")
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
    @Value("#{target.govResponseEntity.id}")
    Long getSyncRequestEntityId();

    @Override
    @Value("#{target.govResponseEntity.subjectNo}")
    String getSubjectNo();

    @Override
    @Value("#{''}")
    String getName();

    @Override
    @Value("#{''}")
    String getStatusVat();
}
