package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import be.quodlibet.boxable.BaseTable;
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.GovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.SearchGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
public class PdfReportService {

    private final PdfTableService pdfTableService;

    @Value("${reports.search}")
    private String SEARCH_FILE_DEST_PATH;

    @Value("${reports.check}")
    private String CHECK_FILE_DEST_PATH;

    private static final String FILE_EXT = ".pdf";
    private static final String CHECK_REPORT_FILE_NAME = "raport_weryfikacji_vat";
    private static final String SEARCH_REPORT_FILE_NAME = "raport_szczegółowy_vat";

    private static final String NEGATIVE_REPORT_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/reject.jpg";
    private static final String POSITIVE_REPORT_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/success.jpg";
    private static final String LOGO_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/fa.png";
    private static final String LOGO_SZW_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/logo_dobra.jpg";

    private static final String CHECK_REPORT_TITLE = "Raport z weryfikacji kontrahenta VAT";
    private static final String SEARCH_REPORT_TITLE = "Raport szczególowy kontrahenta VAT";
    private static final String SEARCH_MULTIPLE_REPORTS_TITLE = "Raport szczególowy dla wielu kontrahentów VAT";

    private static final String NUMERIC_PAGE_FORMAT = " Strona {0}/{0}";
    private static final String RESPONSE_ID = "ID zapytania: ";

    public PdfReportService(PdfTableService pdfTableService) {
        this.pdfTableService = pdfTableService;
    }

    public File preparePdfReport(ReportSyncRequest reportSyncRequest) {
        String reportTile = "";
        File file = null;

        try {
            if (Optional.ofNullable(reportSyncRequest.getGovResponseReportSync()).isPresent()
                    && reportSyncRequest.getGovResponseReportSync() instanceof CheckGovResponseReportSync) {
                reportTile = CHECK_REPORT_TITLE;
                createDir(Path.of(CHECK_FILE_DEST_PATH));
                file = new File(CHECK_FILE_DEST_PATH + File.separator + prepareFileName(reportSyncRequest) + FILE_EXT);
                log.info("FLIE READY: " + file.getPath());
            } else if (Optional.ofNullable(reportSyncRequest.getGovResponseReportSync()).isPresent()
                    && reportSyncRequest.getGovResponseReportSync() instanceof SearchGovResponseReportSync) {
                if (((SearchGovResponseReportSync) reportSyncRequest.getGovResponseReportSync()).getSubjectResponseList().size() > 1) {
                    reportTile = SEARCH_MULTIPLE_REPORTS_TITLE;
                } else {
                    reportTile = SEARCH_REPORT_TITLE;
                }
                createDir(Path.of(SEARCH_FILE_DEST_PATH));
                file = new File(SEARCH_FILE_DEST_PATH + File.separator + prepareFileName(reportSyncRequest) + FILE_EXT);
                log.info("FLIE READY: " + file.getPath());
            }
            startGeneratePdf(file, reportTile, reportSyncRequest);
            //todo czy nazwe raportu zapisywac o to jest pytanie
            log.info(String.format("Report %s generated successful for request id %s. Report file name: %s", reportTile, reportSyncRequest.getGovResponseReportSync().getRequestId(),
                    Optional.ofNullable(file).map(File::getName).orElse("Cant find file name!")));
        } catch (IOException e) {
            String requestID = Optional.ofNullable(reportSyncRequest.getGovResponseReportSync())
                    .map(GovResponseReportSync::getRequestId)
                    .orElse("requestID_ERROR");
            log.error(String.format("ERROR When try to generate %s, request date: %s, responseID: %s", reportTile, reportSyncRequest.getRequestDate().toString(), requestID), e);
            e.printStackTrace();
        }

        return file;
    }

    private String prepareFileName(ReportSyncRequest reportSyncRequest) {
        String requestDate = reportSyncRequest.getRequestDate().toString().replaceAll(":", "-");
        String reportTypeName = reportSyncRequest.getReportType().equals(ReportType.CHECK) ? CHECK_REPORT_FILE_NAME : SEARCH_REPORT_FILE_NAME;
        String requestId = Optional.ofNullable(reportSyncRequest.getGovResponseReportSync()).map(GovResponseReportSync::getRequestId).orElse("NO_REQUEST_ID");
        String reportResult = reportSyncRequest.getSearchResult().equals(SearchResult.NEGATIVE) ? "NEGATYWNY" : "POZYTYWNY";
        String na = "NA_DZIEN";
        String reportDate = reportSyncRequest.getReportDate().toString();
        String sep = "_";
        return new StringBuilder(requestDate)
                .append(sep)
                .append(reportTypeName)
                .append(sep)
                .append(requestId)
                .append(sep)
                .append(reportResult)
                .append(sep)
                .append(na)
                .append(sep)
                .append(reportDate)
                .toString();
    }

    private void startGeneratePdf(File file, String title, ReportSyncRequest reportSyncRequest) throws IOException {
        PDDocument document = new PDDocument();
        pdfDocProperites(document);

        PDPage page = new PDPage();
        document.addPage(page);

        PDRectangle pdRectangle = page.getMediaBox();

        float yCurrentPosition = 700f;
        float xNumbericPagePosition = 200f;
        float yNumbericPagePosition = 18f;

        //prepare logos
        PDImageXObject logoArchimat = prepareImage(document, LOGO_IMAGE);
        float width1LogoScale = logoArchimat.getWidth() / 5f;
        float height1LogoScale = logoArchimat.getHeight() / 5f;
        float y1LogoPosition = yCurrentPosition;
        float x1LogoPosition = (pdRectangle.getWidth()) / 4f;

        PDImageXObject logoSzwalnia = prepareImage(document, LOGO_SZW_IMAGE);
        float width2LogoScale = logoSzwalnia.getWidth() / 10f;
        float height2LogoScale = logoSzwalnia.getHeight() / 10f;
        float y2LogoPosition = yCurrentPosition - 15f;
        float x2LogoPosition = x1LogoPosition * 3 - width1LogoScale;

        //title params
        PDFont titleFont = PDType1Font.TIMES_BOLD_ITALIC;
        int titleFontSize = 22;
        float titleWidth = titleFont.getStringWidth(title) / 1000 * titleFontSize;
        float titleheight = titleFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * titleFontSize;
        float ytitlePos = yCurrentPosition = (yCurrentPosition - 50f);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //logs
        contentStream.drawImage(logoArchimat, x1LogoPosition, y1LogoPosition, width1LogoScale, height1LogoScale);
        contentStream.drawImage(logoSzwalnia, x2LogoPosition, y2LogoPosition, width2LogoScale, height2LogoScale);

        //1-line
        contentStream.setStrokingColor(Color.LIGHT_GRAY);
        contentStream.setLineWidth(1);
        contentStream.moveTo(50, yCurrentPosition = (yCurrentPosition - 10f));
        contentStream.lineTo(pdRectangle.getWidth() - 50, yCurrentPosition);
        contentStream.closeAndStroke();

        //title
        contentStream.beginText();
        contentStream.setFont(titleFont, titleFontSize);
//            contentStream.setLeading(21.5F);
        contentStream.setNonStrokingColor(Color.DARK_GRAY);
        contentStream.newLineAtOffset((pdRectangle.getWidth() - titleWidth) / 2, ytitlePos);
        contentStream.showText(title);
        contentStream.endText();

        //2-line
        contentStream.setStrokingColor(Color.LIGHT_GRAY);
        contentStream.setLineWidth(1);
        contentStream.moveTo(100, ytitlePos + titleheight);
        contentStream.lineTo(pdRectangle.getWidth() - 100, ytitlePos + titleheight);
        contentStream.closeAndStroke();

        //table
        String tableResultImage = SearchResult.NEGATIVE.name().equals(reportSyncRequest.getSearchResult().name()) ? NEGATIVE_REPORT_IMAGE : POSITIVE_REPORT_IMAGE;
        BufferedImage image = prepareImage(document, tableResultImage).getImage();
        BaseTable baseTable = pdfTableService.prepareTable(page, document, yCurrentPosition = (yCurrentPosition - 10f), reportSyncRequest, image);
        baseTable.draw();
//        prepareTable(page, document, yCurrentPosition = (yCurrentPosition - 10f), reportSyncRequest,image).draw();
        contentStream.close();

        //add pages numbers
        String footerPageNumeric = "|" + RESPONSE_ID.concat(reportSyncRequest.getGovResponseReportSync().getRequestId()) + "|" + NUMERIC_PAGE_FORMAT;
        addPageNumbers(document, footerPageNumeric, xNumbericPagePosition, yNumbericPagePosition);

        //save and close doc
        document.save(file);
        document.close();
    }

    private void createDir(Path path) {
        if (!Files.isDirectory(path)) {
            log.info("There is no directory " + path);
            try {
                Files.createDirectories(path);
                log.info("Create directory " + path);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Can not create directory: " + path + "\n" + e);
            }
        }
    }

    private PDImageXObject prepareImage(PDDocument pdDocument, String imagePath) {
        PDImageXObject img = null;
        try {
            img = PDImageXObject.createFromFile(imagePath, pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void pdfDocProperites(PDDocument document) {
        PDDocumentInformation documentInformation = document.getDocumentInformation();
        documentInformation.setAuthor("Maciek");
        documentInformation.setTitle("Raport z białej listy podatników VAT");
        documentInformation.setCreator("whiteSlave APP");
        documentInformation.setSubject("Raport z weryfikacji kontrahenta VAT");

        documentInformation.setModificationDate(Calendar.getInstance());
    }

    private void addPageNumbers(PDDocument document, String numberingFormat, float offset_X, float offset_Y) throws IOException {
        int page_counter = 1;
        for (PDPage page : document.getPages()) {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, false);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ITALIC, 10);
            PDRectangle pageSize = page.getMediaBox();
            float x = pageSize.getLowerLeftX();
            float y = pageSize.getLowerLeftY();
            contentStream.newLineAtOffset(x + pageSize.getWidth() - offset_X, y + offset_Y);
            String text = MessageFormat.format(numberingFormat, page_counter);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
            ++page_counter;
        }
    }

}

