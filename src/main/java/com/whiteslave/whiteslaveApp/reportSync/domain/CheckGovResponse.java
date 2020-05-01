package com.whiteslave.whiteslaveApp.reportSync.domain;

import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CheckGovResponse extends GovResponse {

    private String accountAssigned;

    @Builder
    public CheckGovResponse(String requestId, String accountAssigned) {
        super(requestId);
        this.accountAssigned = accountAssigned;
    }

    @Override
    public String getRequestId() {
        return super.getRequestId();
    }
}
