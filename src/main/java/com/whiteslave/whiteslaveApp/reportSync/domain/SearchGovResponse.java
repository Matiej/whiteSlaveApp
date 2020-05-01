package com.whiteslave.whiteslaveApp.reportSync.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class SearchGovResponse extends GovResponse {

    private List<SubjectResponse> subjectResponseList;

    @Builder
    public SearchGovResponse(String requestId, List<SubjectResponse> subjectResponseList) {
        super(requestId);
        this.subjectResponseList = subjectResponseList;
    }

    @Override
    public String getRequestId() {
        return super.getRequestId();
    }

}
