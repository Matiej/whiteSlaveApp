package com.whiteslave.whiteslaveApp.govRequestReport.searchReport;

import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.*;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
class MfGovSearch2SearchReportDtoConventer {

    public SearchReportDto convertMfSearchResource2SearchReportDto(MfGovSearchResource mfGovSearchResource) {
        MfGovSearchResult result = mfGovSearchResource.getResult();
        return new SearchReportDto()
                .setRequestId(result.getRequestId())
                .setSubjectDtoList(convert2SubjectDto(Optional.ofNullable(result.getMfGovSubject())
                        .map(Collections::singletonList)
                        .orElse(new ArrayList<>())));
    }

    public SearchReportDto convertMfSearchResources2SearchReportDto(final MfGovSearchResources mfGovSearchResources) {
        MfGovSearchResults result = mfGovSearchResources.getResult();
        return new SearchReportDto()
                .setRequestId(result.getRequestId())
                .setSubjectDtoList(convert2SubjectDto(result.getMfGovSubjectList()));
    }

    private List<SubjectDto> convert2SubjectDto(List<MfGovSubject> mfGovSubjects) {
        return Optional.ofNullable(mfGovSubjects)
                .map(l -> l.stream()
                        .map(mfs -> SubjectDto.builder()
                                .name(mfs.getName())
                                .nip(mfs.getNip())
                                .statusVat(mfs.getStatusVat())
                                .regon(mfs.getRegon())
                                .pesel(mfs.getPesel())
                                .krs(mfs.getKrs())
                                .residenceAddress(mfs.getResidenceAddress())
                                .workingAddress(mfs.getWorkingAddress())
                                .representativesDtoList(convert2Representatives(mfs.getMfGovRepresentativesList()))
                                .authorizedClerksDtoList(convert2AuthorizedClerksDto(mfs.getMfGovAuthorizedClerksList()))
                                .partnersDtoList(convert2PartnersDto(mfs.getMfGovPartnersList()))
                                .registrationLegalDate(mfs.getRegistrationLegalDate())
                                .registrationDenialBasis(mfs.getRegistrationDenialBasis())
                                .registrationDenialDate(mfs.getRegistrationDenialDate())
                                .restorationBasis(mfs.getRestorationBasis())
                                .restorationDate(mfs.getRestorationDate())
                                .removalBasis(mfs.getRemovalBasis())
                                .removalDate(mfs.getRemovalDate())
                                .accountNumbersList(mfs.getAccountNumbersList())
                                .hasVirtualAccounts(mfs.getHasVirtualAccounts())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<RepresentativesDto> convert2Representatives(List<MfGovRepresentatives> mfGovRepresentatives) {
        return Optional.ofNullable(mfGovRepresentatives)
                .map(l -> l.stream()
                        .map(mfr -> new RepresentativesDto(mfr.getCompanyName(), mfr.getFirstName(), mfr.getLastName(), mfr.getNip(), mfr.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());

    }

    private List<AuthorizedClerksDto> convert2AuthorizedClerksDto(List<MfGovAuthorizedClerks> mfGovAuthorizedClerks) {
        return Optional.ofNullable(mfGovAuthorizedClerks)
                .map(l -> l.stream()
                        .map(mfa -> new AuthorizedClerksDto(mfa.getCompanyName(), mfa.getFirstName(), mfa.getLastName(), mfa.getNip(), mfa.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private List<PartnersDto> convert2PartnersDto(List<MfGovPartners> mfGovPartners) {
        return Optional.ofNullable(mfGovPartners)
                .map(l -> l.stream()
                        .map(mfg -> new PartnersDto(mfg.getCompanyName(), mfg.getFirstName(), mfg.getLastName(), mfg.getNip(), mfg.getPesel()))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
}
