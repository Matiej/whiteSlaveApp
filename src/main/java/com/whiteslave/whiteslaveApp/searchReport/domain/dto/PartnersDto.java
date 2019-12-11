package com.whiteslave.whiteslaveApp.searchReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnersDto {

    private String companyName, firstName, lastName, nip, pesel;

}
