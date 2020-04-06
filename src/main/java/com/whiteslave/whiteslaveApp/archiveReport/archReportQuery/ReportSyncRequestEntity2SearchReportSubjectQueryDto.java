package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.archiveReport.entity.ReportSyncRequestEntity;
import com.whiteslave.whiteslaveApp.archiveReport.entity.SearchResponseReportEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class ReportSyncRequestEntity2SearchReportSubjectQueryDto {

    public List<SearchReportSubjectQueryDto> convert2Dto(List<ReportSyncRequestEntity> reportSyncRequestEntityList) {
        return reportSyncRequestEntityList.stream()
                .map(entity -> {
                    SearchResponseReportEntity responseReportEntity = (SearchResponseReportEntity) entity.getResponseReportEntity();
                    ReportSyncRequestEntity reportSyncRequestEntity = responseReportEntity.getReportSyncRequestEntity();
                    return new SearchReportSubjectQueryDto(
                            responseReportEntity.getRequestId(),
                            responseReportEntity.getSubjectNo(),
                            0L,
                            "",
                            "",
                            reportSyncRequestEntity.getRequestDate(),
                            reportSyncRequestEntity.getSearchResult(),
                            reportSyncRequestEntity.getRequestNip(),
                            reportSyncRequestEntity.getRequestRegon(),
                            reportSyncRequestEntity.getRequestBankAccount()
                    );
                })
                .collect(Collectors.toList());
    }
}
