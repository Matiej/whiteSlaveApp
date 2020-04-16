package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.BankAccountQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto.CompanyPersonsQueryDto;
import com.whiteslave.whiteslaveApp.archiveReport.entity.RepresentativesResponseEntity;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchReportDetailsQueryView extends SearchPositiveReportQueryView {

    @Override
    @Value("#{target.searchResponseReportEntity.requestId}")
    String getRequestId();

    @Override
    @Value("#{target.id}")
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

    @Value("#{target.searchResponseReportEntity.reportSyncRequestEntity.id}")
    Long getSyncRequestEntityId();

    @Value("#{target.searchResponseReportEntity.subjectNo}")
    String getSubjectNo();

    String getName();

    String getStatusVat();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.representativesEntityList)}")
    List<CompanyPersonsQueryDto> getRepresentativesDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.authorizedClerksResponseEntityList)}")
    List<CompanyPersonsQueryDto> getAuhorizedClerksDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2CompanyPersonsQueryDto(target.partnersResponseEntityList)}")
    List<CompanyPersonsQueryDto> getPartnersDtoList();

    @Value("#{@entity2DtoQueryViewConverter.convert2BankAccountQueryDto(target.bankAccountEntityList)}")
    List<BankAccountQueryDto> getBankAccountDtoList();

}
