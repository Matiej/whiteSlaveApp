package com.whiteslave.whiteslaveApp.reportSync.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class SubjectResponse {

    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    private List<RepresentativesResponse> representativesResponseList;
    private List<AuthorizedClerksResponse> authorizedClerksResponseList;
    private List<PartnersResponse> partnersResponseList;
    private LocalDate registrationLegalDate;
    private String registrationDenialBasis;
    private LocalDate registrationDenialDate;
    private String restorationBasis;
    private LocalDate restorationDate;
    private String removalBasis;
    private LocalDate removalDate;
    private List<String> accountNumbersList;
    private Boolean hasVirtualAccounts;

}
