package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

@Slf4j
public class PdfReportService {

    private static final String POSITIVE_REPORT = "POZYTYWNIE";
    private static final String NEGATIVE_REPORT = "NEGATYWNIE";
    private static final String CHECK_FILE_DEST_PATH = System.getProperty("user.dir") + File.separator + "REPORTS" + File.separator + "CHECK_REPORTS";
    private static final String SEARCH_FILE_DEST_PATH = System.getProperty("user.dir") + File.separator + "REPORTS" + File.separator + "SEARCH_REPORTS";
    private static final String FILE_EXT = ".pdf";
    private static final String CHECK_REPORT_FILE_NAME = "raport_weryfikacji_vat";
    private static final String SEARCH_REPORT_FILE_NAME = "raport_szczegółowy_vat";

    private static final String NEGATIVE_REPORT_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/reject.jpg";
    private static final String POSITIVE_REPORT_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/success.jpg";
    private static final String LOGO_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/fa.png";
    private static final String LOGO_SZW_IMAGE = System.getProperty("user.dir") + File.separator + "src/main/resources/img/logo_dobra.jpg";

    private static final String CHECK_REPORT_TITLE = "Raport z weryfikacji kontrahenta VAT";
    private static final String SEARCH_REPORT_TITLE = "Raport szczegółowy kontrahenta VAT";

    private static final String NUMERIC_PAGE_FORMAT = " Strona {0}/{0}";
    private static final String RESPONSE_ID = "ID zapytania: ";

    public void preparePdfReport(ReportSyncRequest reportSyncRequest) {
        if (Optional.ofNullable(reportSyncRequest.getGovResponseReportSync()).isPresent()
                && reportSyncRequest.getGovResponseReportSync() instanceof CheckGovResponseReportSync) {
            createDir(Path.of(CHECK_FILE_DEST_PATH));
            File file = new File(CHECK_FILE_DEST_PATH + File.separator + prepareFileName(reportSyncRequest) + FILE_EXT);
            log.info("FLIE READY: " + file.getPath());
            try {
                checkStartPdf(file, CHECK_REPORT_TITLE, reportSyncRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String prepareFileName(ReportSyncRequest reportSyncRequest) {
        String reportDate = reportSyncRequest.getReportDate().toString();
        String reportTypeName = reportSyncRequest.getReportType().equals(ReportType.CHECK) ? CHECK_REPORT_FILE_NAME : SEARCH_REPORT_FILE_NAME;
        String param = Optional.ofNullable(reportSyncRequest.getRequestNip()).orElse(reportSyncRequest.getRequestRegon());
        String reportResult = reportSyncRequest.getSearchResult().equals(SearchResult.NEGATIVE) ? "NEGATYWNY" : "POZYTYWNY";
        String reqDate = reportSyncRequest.getRequestDate().toString().replaceAll(":", "-");
        String sep = "_";
        return new StringBuilder(reportDate)
                .append(sep)
                .append(reportTypeName)
                .append(sep)
                .append(param)
                .append(sep)
                .append(reportResult)
                .append(sep)
                .append(reqDate)
                .toString();
    }

    private void checkStartPdf(File file, String title, ReportSyncRequest reportSyncRequest) throws IOException {
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
        prepareTable(page, document, yCurrentPosition = (yCurrentPosition - 10f), reportSyncRequest).draw();
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

    //todo tutaj mają przyjść parametry lub mapa parametrów z tekstem, czy negatywny ze zdjęciem itp .
    private BaseTable prepareTable(PDPage page, PDDocument document, float yLastPos, ReportSyncRequest reportSyncRequest) throws IOException {
        final String reportVerResult = SearchResult.NEGATIVE.name().equals(reportSyncRequest.getSearchResult().name()) ? NEGATIVE_REPORT : POSITIVE_REPORT;
        float margin = 50;
        float yStartNewPage = page.getMediaBox().getHeight() - 100; //2xmargin
        float tableWidth = page.getMediaBox().getWidth() - 100;

        boolean drawContent = true;
        float bottomMargin = 70;

        BaseTable table = new BaseTable(yLastPos, yStartNewPage,
                bottomMargin, tableWidth, margin, document, page, true, drawContent);

        //-> first table row + image positive/negative
        // the parameter is the row height
        Row<PDPage> headerRow = table.createRow(20);
        // the first parameter is the cell width
        Cell<PDPage> cell = headerRow.createCell(90, "Kontrahent zweryfikowany " + reportVerResult);
        cell.setFont(PDType1Font.HELVETICA_BOLD);
        cell.setFontSize(18);
        // vertical alignment
        cell.setValign(VerticalAlignment.MIDDLE);
        // border style
        cell.setTopBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        //image for first table title row
        String tableResultImage = SearchResult.NEGATIVE.name().equals(reportSyncRequest.getSearchResult().name()) ? NEGATIVE_REPORT_IMAGE : POSITIVE_REPORT_IMAGE;
        BufferedImage image = prepareImage(document, tableResultImage).getImage();
        be.quodlibet.boxable.image.Image statusImg = new Image(image);
        cell = headerRow.createImageCell(10, statusImg, HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setTopBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        table.addHeaderRow(headerRow);

        //header row for request params
        Row<PDPage> smallHeaderRow1 = table.createRow(16);
        cell = smallHeaderRow1.createCell(100,"PRZEKAZANE PARAMETRY ZAPYTANIA");
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setFontSize(15);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.CENTER);
        table.addHeaderRow(smallHeaderRow1);

        //request paramas
        String firstParamName = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? "NIP" : "REGON";
        String firstParam = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? reportSyncRequest.getRequestNip() : reportSyncRequest.getRequestRegon();
        String secondParamName = "NUMER KONTA BANKOWEGO";
        String secondParam = reportSyncRequest.getRequestBankAccount();
        String firstParamCell = firstParamName + ": " + firstParam;
        String secondParamCell = secondParamName + ": " + secondParam;

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(40, firstParamCell);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(60, secondParamCell);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);

        String formatedRequestDate = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss").format(reportSyncRequest.getRequestDate().withNano(0));
        String requestDateParam = "DATA ZAPYTANIA: ".concat(formatedRequestDate);
        String reportType = ReportType.CHECK.name().equals(reportSyncRequest.getReportType().name()) ? "SZYBKA_WERYFIKACJA" : "RAPORT_SZCZEGÓLOWY";
        String reportTypeParam = "TYP RAPORTU: ".concat(reportType);

        row = table.createRow(20);
        cell = row.createCell(40, reportTypeParam);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(60, requestDateParam);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);


        if(0>1) {
//            Row<PDPage> row = table.createRow(16);
            cell = row.createCell(30, "Kontrahent: ");
            cell.setFontSize(14);
            cell.setAlign(HorizontalAlignment.LEFT);
            cell = row.createCell(70, "black left bold");
            cell.setFontSize(14);
            cell.setFont(PDType1Font.HELVETICA_BOLD);

            row = table.createRow(20);
            cell = row.createCell(50, "red right mono");
            cell.setTextColor(Color.RED);
            cell.setFontSize(15);
            cell.setFont(PDType1Font.COURIER);
            // horizontal alignment
            cell.setAlign(HorizontalAlignment.RIGHT);
            cell.setBottomBorderStyle(new LineStyle(Color.RED, 5));
            cell = row.createCell(50, "green centered italic");
            cell.setTextColor(Color.GREEN);
            cell.setFontSize(15);
            cell.setFont(PDType1Font.TIMES_ITALIC);
            cell.setAlign(HorizontalAlignment.CENTER);
            cell.setBottomBorderStyle(new LineStyle(Color.GREEN, 5));

            row = table.createRow(20);
            cell = row.createCell(40, "rotated");
            cell.setFontSize(15);
            // rotate the text
            cell.setTextRotated(true);
            cell.setAlign(HorizontalAlignment.RIGHT);
            cell.setValign(VerticalAlignment.MIDDLE);
            // long text that wraps
            cell = row.createCell(30, "long text long text long text long text long text long text long text");
            cell.setFontSize(12);
            // long text that wraps, with more line spacing
            cell = row.createCell(30, "long text long text long text long text long text long text long text");
            cell.setFontSize(12);
            cell.setLineSpacing(2);
        }

        return table;

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
