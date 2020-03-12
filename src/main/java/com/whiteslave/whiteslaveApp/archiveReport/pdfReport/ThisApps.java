package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.GovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ThisApps {

    public static void main(String[] args) {
        GovResponseReportSync res = new CheckGovResponseReportSync("sdqwsr41211000", "TAKKKK");
        ReportSyncRequest r = ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.now())
                .searchResult(SearchResult.NEGATIVE)
                .reportType(ReportType.SEARCH)
                .requestNip("111811818")
//                .requestRegon("012442100")
                .requestBankAccount("1231231232168954564454645645")
                .govResponseReportSync(res)
                .build();

        PdfReportService p = new PdfReportService();
        p.preparePdfReport(r);

//        float yStartPos = 700f;
//        System.out.println("cos tam1");
//        float yLogoStartPos = yStartPos = (yStartPos -15f);
//        System.out.println("moje nowego yLogoStartPos-> " + yLogoStartPos);
//        System.out.println("moje nowego yStart-> " + yStartPos);
    }
}
