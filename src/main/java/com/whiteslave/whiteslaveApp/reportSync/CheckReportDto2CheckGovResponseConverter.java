package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponse;
import org.springframework.stereotype.Component;

@Component
class CheckReportDto2CheckGovResponseConverter {

    public CheckGovResponse convertToCheckGovReportSync(CheckReportDto checkReportDto) {
        return CheckGovResponse.builder()
                .requestId(checkReportDto.getRequestId())
                .accountAssigned(checkReportDto.getAccountAssigned())
                .build();
    }
}
