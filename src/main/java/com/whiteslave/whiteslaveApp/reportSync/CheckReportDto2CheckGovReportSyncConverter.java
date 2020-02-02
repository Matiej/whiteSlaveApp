package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import org.springframework.stereotype.Component;

@Component
class CheckReportDto2CheckGovReportSyncConverter {

    public CheckGovResponseReportSync convertToCheckGovReportSync(CheckReportDto checkReportDto) {
        return CheckGovResponseReportSync.builder()
                .requestId(checkReportDto.getRequestId())
                .accountAssigned(checkReportDto.getAccountAssigned())
                .build();
    }
}
