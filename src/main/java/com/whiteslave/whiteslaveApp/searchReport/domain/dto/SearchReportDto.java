package com.whiteslave.whiteslaveApp.searchReport.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SearchReportDto {

    private String requestId;
    private List<SubjectDto> subjectDtoList;
}