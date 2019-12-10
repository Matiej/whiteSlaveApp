package com.whiteslave.whiteslaveApp.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    @JsonProperty("representatives")
    private List<Representatives> representativesList;

    @JsonProperty("authorizedClerks")
    private List<AuthorizedClerks> authorizedClerksList;

    @JsonProperty("partners")
    private List<Partners> partnersList;

    private LocalDate registrationLegalDate;
    private String registrationDenialBasis;
    private LocalDate registrationDenialDate;
    private String restorationBasis;
    private LocalDate restorationDate;
    private String removalBasis;
    private LocalDate removalDate;

    @JsonProperty("accountNumbers")
    private List<String> accountNumbersList;
    private Boolean hasVirtualAccounts;

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", nip='" + nip + '\'' +
                ", statusVat='" + statusVat + '\'' +
                ", regon='" + regon + '\'' +
                ", pesel='" + pesel + '\'' +
                ", krs='" + krs + '\'' +
                ", residenceAddress='" + residenceAddress + '\'' +
                ", workingAddress='" + workingAddress + '\'' +
                ", representativesList=" + representativesList +
                ", authorizedClerksList=" + authorizedClerksList +
                ", partnersList=" + partnersList +
                ", registrationLegalDate=" + registrationLegalDate +
                ", registrationDenialBasis='" + registrationDenialBasis + '\'' +
                ", registrationDenialDate='" + registrationDenialDate + '\'' +
                ", restorationBasis='" + restorationBasis + '\'' +
                ", restorationDate='" + restorationDate + '\'' +
                ", removalBasis='" + removalBasis + '\'' +
                ", removalDate=" + removalDate +
                ", accountNumbersList=" + accountNumbersList +
                ", hasVirtualAccounts=" + hasVirtualAccounts +
                '}';
    }
}


