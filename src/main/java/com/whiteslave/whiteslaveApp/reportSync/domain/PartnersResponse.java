package com.whiteslave.whiteslaveApp.reportSync.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnersResponse {

    private String companyName, firstName, lastName, nip, pesel;

}
