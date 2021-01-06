package com.whiteslave.whiteslaveApp.archiveReport;

import com.whiteslave.whiteslaveApp.archiveReport.pdfReport.PdfReportPackageTestConfig;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@SpringJUnitConfig(classes = {ArchiveReportTestConfig.class, PdfReportPackageTestConfig.class})
class ArchReportServiceTest extends ArchReportServiceTestBase {

    private final static String TEST_PATH = "src/test/resources/testPdfReport";

    @Test
    void generateAndSaveReportPdf() {
    }

    @Test
    @DisplayName("Check version report generation test. Generate and save pdf on the server")
    void testGenerateAndSaveReportCheckPdf(@Autowired ArchReportService archReportService) {
        //given
        ReportSyncRequest reportSyncRequest = buildCheckReportSyncRequest();
        String expectFileName = "2020-11-28T12-35-44_raport_weryfikacji_vat_id123_POZYTYWNY_NA_DZIEN_2020-10-22.pdf";
        //when

        File file = archReportService.generateAndSaveReportPdf(reportSyncRequest);

        //then
        assertNotNull(file);
        assertThat(file.getName()).isEqualTo(expectFileName);
    }

    @AfterAll
    static void tearDown(TestInfo testInfo) throws IOException {
        File file = new File(TEST_PATH);
        Path dir = file.getAbsoluteFile().toPath();
        log.info("Trying to clean after test: " + testInfo.getDisplayName());
        if (Files.exists(dir)) {
            Stream<Path> list = Files.list(dir);
            list.forEach(path -> {
                try {
                    log.info("Deleteing file-> " + path.toString());
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Files.delete(dir);
            log.info("Directory was deleted: " + dir);
        } else {
            log.warn("Can not delete directory " + dir + ", not exist but should!");
        }
    }

    private ReportSyncRequest buildCheckReportSyncRequest() {
        return ArchReportServiceTestBase.builder()
                .withGovResponse(ReportSyncRequestBuilder.checkBuilder()
                        .withRequestId("id123")
                        .withAccountAssignedTakPositive().build())
                .withSearchResult(SearchResult.POSITIVE)
                .withReportType(ReportType.CHECK)
                .build();
    }
}