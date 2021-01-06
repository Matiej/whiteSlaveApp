package com.whiteslave.whiteslaveApp.govRequestReport.searchReport;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovErrorDecoder;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovExceptionMessage;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import feign.Feign;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.SocketUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertNull;

class SearchReportServiceTest {

    int port = SocketUtils.findAvailableTcpPort();

    WireMockServer wireMockServer;

    private MfGovWhiteListClient mfGovWhiteListClient;

    @Mock
    private MfGovSearch2SearchReportDtoConventer conventer;

    @InjectMocks
    private SearchReportServiceImpl searchReportService;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor(port);
        this.mfGovWhiteListClient = Feign.builder()
                .errorDecoder(new MfGovErrorDecoder())
                .target(MfGovWhiteListClient.class, "http://localhost:" + port);

    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void searchByNipAndDate() {
        String nip = "9482025858";
        String date = "2020-11-01";
        MfGovExceptionMessage mfGovExceptionMessage = new MfGovExceptionMessage("Nieprawidłowy NIP (9482025857).","WL-115");
//        given(conventer.convertMfSearchResource2SearchReportDto(any())).


        wireMockServer.stubFor(WireMock.get("/search/nip/date?date=" + date + "&nip=" + nip)
                .willReturn(aResponse().withStatus(400).withBody("ss")));

        SearchReportDto searchReportDto = searchReportService.searchByNipAndDate(nip, date);

        assertNull(searchReportDto);


    }

}