package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ThisApps {

    public static void main(String[] args) {
        GovResponseReportSync res = new CheckGovResponseReportSync("sdqwsr41211000", "TAK");


        SubjectResponse s1 = SubjectResponse.builder()
                .name("DIMU SPÓLKA Z OGRANICZONA ODPOWIEDZIALNOSCIA")
                .residenceAddress("ul. Chlopickiego 48, 05-080 Izabelin")
                .statusVat("Czynny")
                .nip("1181481416")
                .regon("00000011112222")
                .krs("001464655")
                .registrationLegalDate(LocalDate.of(1979,7,25))
                .hasVirtualAccounts(false)
                .build();


        GovResponseReportSync searchRes = new SearchGovResponseReportSync("search41211000", List.of(s1, s1));

        ReportSyncRequest r = ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.now())
                .searchResult(SearchResult.POSITIVE)
                .reportType(ReportType.CHECK)
                .requestNip("111811818")
//                .requestRegon("012442100")
                .requestBankAccount("1231231232168954564454645645")
                .govResponseReportSync(res)
                .build();

        ReportSyncRequest searchRequest = ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.now())
                .searchResult(SearchResult.POSITIVE)
                .reportType(ReportType.SEARCH)
//                .requestNip("")
//                .requestRegon("012442100,00000011112222,21412412412,1241243532523")
                .requestRegon("00000011112222, 00000011112222")
//                .requestBankAccount("1231231232168954564454645645")
                .govResponseReportSync(searchRes)
                .build();

        PdfReportService p = new PdfReportService(new PdfTableService());
//        p.preparePdfReport(r);
        p.preparePdfReport(searchRequest);


    }
}
