package com.whiteslave.whiteslaveApp.reportSync.entity;

import java.time.LocalDate;

public class SubjectEntity {

    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
//    private List<RepresentativesDto> representativesDtoList;
//    private List<AuthorizedClerksDto> authorizedClerksDtoList;
//    private List<PartnersDto> partnersDtoList;
    private LocalDate registrationLegalDate;
    private String registrationDenialBasis;
    private LocalDate registrationDenialDate;
    private String restorationBasis;
    private LocalDate restorationDate;
    private String removalBasis;
    private LocalDate removalDate;
    private String accountNumbers;
    private Boolean hasVirtualAccounts;
}
