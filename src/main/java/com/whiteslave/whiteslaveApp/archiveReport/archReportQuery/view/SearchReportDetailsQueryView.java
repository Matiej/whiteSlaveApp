package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.BankAccountQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.CompanyPersonsQueryDto;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchReportDetailsQueryView extends SearchPositiveReportQueryView {

    @Override
    @Value("#{target.govSearchResponseEntity.requestId}")
    String getRequestId();

    @Override
    @Value("#{target.id}")
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

    @Override
    String getName();

    @Override
    String getStatusVat();

    @Value("#{target.govSearchResponseEntity.reportSyncRequestEntity.reportType}")
    String getReportType();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.representativesEntityList)}")
    List<CompanyPersonsQueryDto> getRepresentativesDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.authorizedClerksResponseEntityList)}")
    List<CompanyPersonsQueryDto> getAuhorizedClerksDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.partnersResponseEntityList)}")
    List<CompanyPersonsQueryDto> getPartnersDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2BankAccountQueryDto(target.bankAccountEntityList)}")
    List<BankAccountQueryDto> getBankAccountDtoList();

}
