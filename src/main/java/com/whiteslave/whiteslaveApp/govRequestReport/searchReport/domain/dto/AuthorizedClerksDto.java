package com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedClerksDto {

    private String companyName, firstName, lastName, nip, pesel;

}
