package com.whiteslave.whiteslaveApp.checkReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckReportDto {

    private String accountAssigned;
    private String requestId;
}
