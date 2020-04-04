package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.entity.BankAccountEntity;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchReportSubjectQueryView {

    List<BankAccountEntity> getBankAccountEntityList();

    @Value("#{target.searchResponseReportEntity.requestId}")
    String getRequestId();

    @Value("#{target.searchResponseReportEntity.subjectNo}")
    String getSubjectNo();

    Long getId();

    String getName();

    String getNip();

    String getStatusVat();

    String getRegon();

    String getPesel();

    String getKrs();

    String getResidenceAddress();

    String getWorkingAddress();

    LocalDate getRegistrationLegalDate();

    String getRegistrationDenialBasis();

    LocalDate getRegistrationDenialDate();

    String getRestorationBasis();

    LocalDate getRestorationDate();

    String getRemovalBasis();

    LocalDate getRemovalDate();

    Boolean getHasVirtualAccounts();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestDate}")
    LocalDateTime getRequestDate();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.reportDate}")
    LocalDate getReportDate();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.searchResult}")
    String getSearchResult();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestNip}")
    String getRequestNip();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestRegon}")
    String getRequestRegon();

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.requestBankAccount}")
    String getRequestBankAccount();
}
