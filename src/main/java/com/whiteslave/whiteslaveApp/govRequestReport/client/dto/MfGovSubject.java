package com.whiteslave.whiteslaveApp.govRequestReport.client.dto;

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
public class MfGovSubject {

    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    @JsonProperty("representatives")
    private List<MfGovRepresentatives> mfGovRepresentativesList;

    @JsonProperty("authorizedClerks")
    private List<MfGovAuthorizedClerks> mfGovAuthorizedClerksList;

    @JsonProperty("partners")
    private List<MfGovPartners> mfGovPartnersList;

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
                ", representativesList=" + mfGovRepresentativesList +
                ", authorizedClerksList=" + mfGovAuthorizedClerksList +
                ", partnersList=" + mfGovPartnersList +
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


