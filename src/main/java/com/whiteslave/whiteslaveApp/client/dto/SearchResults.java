package com.whiteslave.whiteslaveApp.client.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription(value = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResults {

    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("subjects")
    private List<Subject> subjectList;
}
