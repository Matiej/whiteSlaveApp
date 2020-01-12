package com.whiteslave.whiteslaveApp.checkReport;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.client.dto.MfGovCheckResource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class MfGovCheck2CheckReportDtoConverter {

    public CheckReportDto convertMfGovCheck2CheckReportDto(MfGovCheckResource mfGovCheckResource) {
        return Optional.of(mfGovCheckResource.getResult())
                .map(mfc -> CheckReportDto.builder()
                        .requestId(mfc.getRequestId())
                        .accountAssigned(mfc.getAccountAssigned())
                        .build())
                .orElse(new CheckReportDto());
    }
}
