package com.whiteslave.whiteslaveApp.archiveReport.archReportQuery;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class SearchReportSubjectQueryDto {

    private String searchResponseReportEntityRequestId;
    private Integer searchResponseReportEntitySubjectNo;
    private Long id;
    private String name;
    private String statusVat;
    private LocalDateTime searchResponseReportEntityReportSyncRequestEntityRequestDate;
    private SearchResult  searchResponseReportEntityReportSyncRequestEntitySearchResult;
    private String searchResponseReportEntityReportSyncRequestEntityRequestNip;
    private String searchResponseReportEntityReportSyncRequestEntityRequestRegon;
    private String searchResponseReportEntityReportSyncRequestEntityRequestBankAccount;

}
