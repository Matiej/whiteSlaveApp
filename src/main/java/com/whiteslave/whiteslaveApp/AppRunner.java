package com.whiteslave.whiteslaveApp;

import com.whiteslave.whiteslaveApp.archiveReport.pdfReport.PdfReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.MfGovSearchResource;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.SearchReportService;
import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private MfGovWhiteListClient mfGovWhiteListClient;

    @Autowired
    private SearchReportService searchReportService;

    @Autowired
    private PdfReportService pdfReportService;


    @Override
    public void run(String... args) throws Exception {
//    pdfTest();
        try {
//            testHandlErrors();
        } catch                                                                                                                                                                  (FeignException e) {
            e.printStackTrace();
        }

//        pdfTest();

        }

        public void testHandlErrors () {
            MfGovSearchResource mfGovSearchResource = mfGovWhiteListClient.searchByNipAndDate("9482025859", "2020-01-01");
            System.out.println(String.format("REQUEST ID: %s", mfGovSearchResource.getResult().getRequestId()));
        }

        public void pdfTest () {
            GovResponseReportSync res = new CheckGovResponseReportSync("sdqwsr41211000", "TAK");

            CompanyPersons companyPersons = new CompanyPersons();
            companyPersons.setCompanyName("COmapnyNN SPZ");
            companyPersons.setFirstName("Maciek");
            companyPersons.setLastName("Wojcik");
            companyPersons.setNip("PL11814184");
            companyPersons.setPesel("7907080725");

            List<CompanyPersons> companyPersonsList = new LinkedList<>();
            companyPersonsList.add(companyPersons);

            List<CompanyPersons> companyPersonsListAA = new LinkedList<>();
            companyPersonsListAA.add(companyPersons);
            companyPersonsListAA.add(companyPersons);
            companyPersonsListAA.add(companyPersons);
            SubjectResponse s1 = SubjectResponse.builder()
                    .name("DIMU SPÓLKA Z OGRANICZONA ODPOWIEDZIALNOSCIA")
                    .residenceAddress("ul. Chlopickiego 48, 05-080 Izabelin")
                    .statusVat("Czynny")
                    .nip("1181481416")
                    .regon("00000011112222")
                    .krs("001464655")
                    .registrationLegalDate(LocalDate.of(1979, 7, 25))
                    .hasVirtualAccounts(false)
                    .representativesResponseList(companyPersonsList)
                    .authorizedClerksResponseList(companyPersonsList)
                    .partnersResponseList(companyPersonsListAA)
                    .accountNumbersList(List.of("109840912849049032849032","222240912849049032849032","4444440912849049032849032"))
                    .registrationDenialDate(LocalDate.now().minusDays(50L))
                    .registrationDenialBasis("DENIAL BASIS REG")
                    .removalDate(LocalDate.now().minusDays(150L))
                    .removalBasis("REMOVA  basis")
                    .restorationDate(LocalDate.now())
                    .restorationBasis("restoration basis")
                    .build();


            GovResponseReportSync searchRes = new SearchGovResponseReportSync("search41211000", List.of(s1));

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
                    .requestRegon("00000011112222, 00000011112222, 921202222")
//                .requestBankAccount("1231231232168954564454645645")
                    .govResponseReportSync(searchRes)
                    .build();


//        p.preparePdfReport(r);
            pdfReportService.preparePdfReport(searchRequest);

        }
    }
