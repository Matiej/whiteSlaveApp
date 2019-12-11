package com.whiteslave.whiteslaveApp.searchReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)
@Builder
public class SubjectDto {
//todo zrobic buildera, ktory bedzie obejmowal wszystkie pola
    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    private List<RepresentativesDto> representativesDtoList;
    private List<AuthorizedClerksDto> authorizedClerksDtoList;
    private List<PartnersDto> partnersDtoList;
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
