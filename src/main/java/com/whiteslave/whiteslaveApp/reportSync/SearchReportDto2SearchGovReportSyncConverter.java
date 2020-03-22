package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class SearchReportDto2SearchGovReportSyncConverter {

    public SearchGovResponseReportSync convertToSearchGovResponseReportSync(SearchReportDto searchReportDto) {
        SearchGovResponseReportSync reportSync = SearchGovResponseReportSync.builder()
                .requestId(searchReportDto.getRequestId())
                .subjectResponseList(convertSubjectDto2SubjectResponse(searchReportDto.getSubjectDtoList()))
                .build();

        return reportSync;
    }

    private List<SubjectResponse> convertSubjectDto2SubjectResponse(List<SubjectDto> subjectDtoList) {
        return Optional.ofNullable(subjectDtoList)
                .map(s -> s.stream()
                        .map(sub -> SubjectResponse.builder()
                                .name(sub.getName())
                                .nip(sub.getNip())
                                .statusVat(sub.getStatusVat())
                                .regon(sub.getRegon())
                                .pesel(sub.getPesel())
                                .krs(sub.getKrs())
                                .residenceAddress(sub.getResidenceAddress())
                                .workingAddress(sub.getWorkingAddress())
                                .representativesResponseList(convertRepresentativesDto2RepresentativesResponse(sub.getRepresentativesDtoList()))
                                .authorizedClerksResponseList(convertAuthorizedClerksDto2AuthorizedClerksResponse(sub.getAuthorizedClerksDtoList()))
                                .partnersResponseList(convertPartnersDto2PartnersResponse(sub.getPartnersDtoList()))
                                .registrationLegalDate(sub.getRegistrationLegalDate())
                                .registrationDenialDate(sub.getRegistrationDenialDate())
                                .registrationDenialBasis(sub.getRegistrationDenialBasis())
                                .restorationBasis(sub.getRestorationBasis())
                                .restorationDate(sub.getRestorationDate())
                                .removalBasis(sub.getRemovalBasis())
                                .removalDate(sub.getRemovalDate())
                                .accountNumbersList(sub.getAccountNumbersList())
                                .hasVirtualAccounts(sub.getHasVirtualAccounts())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

    }

    private List<CompanyPersons> convertRepresentativesDto2RepresentativesResponse(List<RepresentativesDto> representativesDtoList) {
        return Optional.ofNullable(representativesDtoList)
                .map(r -> r.stream()
                        .map(representatives -> new CompanyPersons(
                                representatives.getCompanyName(),
                                representatives.getFirstName(),
                                representatives.getLastName(),
                                representatives.getNip(),
                                representatives.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<CompanyPersons> convertAuthorizedClerksDto2AuthorizedClerksResponse(List<AuthorizedClerksDto> authorizedClerksDtoList) {
        return Optional.ofNullable(authorizedClerksDtoList)
                .map(c -> c.stream()
                        .map(clerk -> new CompanyPersons(
                                clerk.getCompanyName(),
                                clerk.getFirstName(),
                                clerk.getLastName(),
                                clerk.getNip(),
                                clerk.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<CompanyPersons> convertPartnersDto2PartnersResponse(List<PartnersDto> partnersDtoList) {
        return Optional.ofNullable(partnersDtoList)
                .map(p -> p.stream()
                        .map(partner -> new CompanyPersons(
                                partner.getCompanyName(),
                                partner.getFirstName(),
                                partner.getLastName(),
                                partner.getNip(),
                                partner.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
