package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.reportSync.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class ReportSyncRequest2EntityConverter {

    public ReportSyncRequestEntity convert2Entity(ReportSyncRequest reportSyncRequest) {
        ReportSyncRequestEntity entity = new ReportSyncRequestEntity();
        entity.setRequestDate(reportSyncRequest.getRequestDate());
        entity.setReportDate(reportSyncRequest.getReportDate());
        entity.setSearchResult(reportSyncRequest.getSearchResult());
        entity.setReportType(reportSyncRequest.getReportType());
        entity.setRequestNip(reportSyncRequest.getRequestNip());
        entity.setRequestRegon(reportSyncRequest.getRequestRegon());
        entity.setRequestBankAccount(reportSyncRequest.getRequestBankAccount());

        GovResponseReportSync govResponseReportSync = reportSyncRequest.getGovResponseReportSync();
        if (govResponseReportSync instanceof CheckGovResponseReportSync) {
            entity.setResponseReportEntity(covertCheckGovReportSync2ReportEntity((CheckGovResponseReportSync) govResponseReportSync));
        }
        if (govResponseReportSync instanceof SearchGovResponseReportSync) {
            entity.setResponseReportEntity(convertSearchGovReportSync2ReportEntity((SearchGovResponseReportSync) govResponseReportSync));
        }
        return entity;
    }

    private CheckResponseReportEntity covertCheckGovReportSync2ReportEntity(CheckGovResponseReportSync checkGovReportSync) {
        CheckResponseReportEntity entity = new CheckResponseReportEntity();
        entity.setRequestId(checkGovReportSync.getRequestId());
        entity.setAccountAssigned(checkGovReportSync.getAccountAssigned());
        return entity;
    }

    private SearchResponseReportEntity convertSearchGovReportSync2ReportEntity(SearchGovResponseReportSync searchGovResponseReportSync) {
        SearchResponseReportEntity entity = new SearchResponseReportEntity();
        entity.setRequestId(searchGovResponseReportSync.getRequestId());
        entity.setSubjectNo(searchGovResponseReportSync.getSubjectResponseList().size());
        entity.setSubjectEntityList(convertSubject2Entity(searchGovResponseReportSync.getSubjectResponseList()));

        return entity;
    }

    private List<SubjectEntity> convertSubject2Entity(List<SubjectResponse> subjectResponseList) {
        return Optional.ofNullable(subjectResponseList)
                .map(subjectResponses -> subjectResponses.stream()
                        .map(sub -> SubjectEntity.builder()
                                .name(sub.getName())
                                .nip(sub.getNip())
                                .statusVat(sub.getStatusVat())
                                .regon(sub.getRegon())
                                .pesel(sub.getPesel())
                                .krs(sub.getKrs())
                                .residenceAddress(sub.getResidenceAddress())
                                .workingAddress(sub.getWorkingAddress())
                                .representativesEntityList(convertRepresentatives2Entity(sub.getRepresentativesResponseList()))
                                .authorizedClerksResponseEntityList(convertAuthorizedClerksRespone2Entity(sub.getAuthorizedClerksResponseList()))
                                .partnersResponseEntityList(converPartnerResponse2Entity(sub.getPartnersResponseList()))
                                .registrationLegalDate(sub.getRegistrationLegalDate())
                                .registrationDenialBasis(sub.getRegistrationDenialBasis())
                                .registrationDenialDate(sub.getRegistrationDenialDate())
                                .restorationBasis(sub.getRestorationBasis())
                                .restorationDate(sub.getRestorationDate())
                                .removalBasis(sub.getRemovalBasis())
                                .removalDate(sub.getRemovalDate())
                                .accountNumbers(String.join(",", sub.getAccountNumbersList()))
                                .hasVirtualAccounts(sub.getHasVirtualAccounts())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<RepresentativesResponseEntity> convertRepresentatives2Entity(List<RepresentativesResponse> representativesResponseList) {
        return Optional.ofNullable(representativesResponseList)
                .map(r -> r.stream()
                        .map(rep -> RepresentativesResponseEntity.builder()
                                .companyName(rep.getCompanyName())
                                .firstName(rep.getFirstName())
                                .lastName(rep.getLastName())
                                .nip(rep.getNip())
                                .pesel(rep.getPesel())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<AuthorizedClerksResponseEntity> convertAuthorizedClerksRespone2Entity(List<AuthorizedClerksResponse> authorizedClerksResponseList) {
        return Optional.ofNullable(authorizedClerksResponseList)
                .map(a -> a.stream()
                        .map(clerksResponse -> AuthorizedClerksResponseEntity.builder()
                                .companyName(clerksResponse.getCompanyName())
                                .firstName(clerksResponse.getFirstName())
                                .lastName(clerksResponse.getLastName())
                                .nip(clerksResponse.getNip())
                                .pesel(clerksResponse.getPesel())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }


    private List<PartnersResponseEntity> converPartnerResponse2Entity(List<PartnersResponse> partnersResponseList) {
        return Optional.ofNullable(partnersResponseList)
                .map(p -> p.stream()
                        .map(partner -> PartnersResponseEntity.builder()
                                .companyName(partner.getCompanyName())
                                .firstName(partner.getFirstName())
                                .lastName(partner.getLastName())
                                .nip(partner.getNip())
                                .pesel(partner.getPesel())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }


}
