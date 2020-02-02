package com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepresentativesDto {

    private String companyName, firstName, lastName, nip, pesel;

}
