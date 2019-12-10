package com.whiteslave.whiteslaveApp.searchReport.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiteslave.whiteslaveApp.client.dto.AuthorizedClerks;
import com.whiteslave.whiteslaveApp.client.dto.Partners;
import com.whiteslave.whiteslaveApp.client.dto.Representatives;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchReportDto {



    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    private List<Representatives> representativesList;
    private List<AuthorizedClerks> authorizedClerksList;
    private List<Partners> partnersList;
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