package com.whiteslave.whiteslaveApp.govRequestReport.checkReport;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.MfGovCheckResource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class MfGovCheck2CheckReportDtoConverter {

    public CheckReportDto convertMfGovCheck2CheckReportDto(final MfGovCheckResource mfGovCheckResource) {
        return Optional.of(mfGovCheckResource.getResult())
                .map(mfc -> CheckReportDto.builder()
                        .requestId(mfc.getRequestId())
                        .accountAssigned(mfc.getAccountAssigned())
                        .build())
                .orElse(new CheckReportDto());
    }
}
