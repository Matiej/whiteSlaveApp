package com.whiteslave.whiteslaveApp.reportSync.domain;

import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CheckGovResponseReportSync extends GovResponseReportSync {

    private String accountAssigned;

    @Builder
    public CheckGovResponseReportSync(String requestId, String accountAssigned) {
        super(requestId);
        this.accountAssigned = accountAssigned;
    }

    @Override
    public String getRequestId() {
        return super.getRequestId();
    }
}
