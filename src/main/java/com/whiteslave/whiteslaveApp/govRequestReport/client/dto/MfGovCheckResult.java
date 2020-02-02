package com.whiteslave.whiteslaveApp.govRequestReport.client.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription(value = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfGovCheckResult {

    private String accountAssigned;
    private String requestId;
}
