package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPersonsQueryDto {
    private Long id;
    private String companyName, firstName, lastName, nip, pesel;

}
