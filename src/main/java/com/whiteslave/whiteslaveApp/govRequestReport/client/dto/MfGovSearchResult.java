package com.whiteslave.whiteslaveApp.govRequestReport.client.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription(value = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfGovSearchResult {

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("subject")
    private MfGovSubject mfGovSubject;


}
