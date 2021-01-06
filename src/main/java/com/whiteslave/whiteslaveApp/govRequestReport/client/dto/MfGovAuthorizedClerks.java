package com.whiteslave.whiteslaveApp.govRequestReport.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)//jeżeli sa null to nie będą serializowane i nie będize problemu. Zostanie u nas null na polu.
@JsonIgnoreProperties(ignoreUnknown = true)//pozwala ignorowac nie mapowane
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfGovAuthorizedClerks {

    private String companyName, firstName, lastName, nip, pesel;
}
