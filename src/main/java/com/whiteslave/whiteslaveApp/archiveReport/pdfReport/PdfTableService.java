package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.SearchGovResponseReportSync;
import com.whiteslave.whiteslaveApp.reportSync.domain.SubjectResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class PdfTableService {

    private static final String POSITIVE_REPORT = "POZYTYWNIE";
    private static final String NEGATIVE_REPORT = "NEGATYWNIE";
    private static final String BRAK_DANYCH = "BRAK_DANYCH";

    public BaseTable prepareTable(PDPage page, PDDocument document, float yLastPos, ReportSyncRequest reportSyncRequest, BufferedImage image) throws IOException {

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
        be.quodlibet.boxable.image.Image statusImg = new Image(image);
        cell = headerRow.createImageCell(10, statusImg, HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
        cell.setTopBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        table.addHeaderRow(headerRow);

        //header row for request params
        Row<PDPage> smallHeaderRequestRow = table.createRow(16);
        cell = smallHeaderRequestRow.createCell(100, "PRZEKAZANE PARAMETRY ZAPYTANIA");
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setFontSize(15);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.CENTER);
        table.addHeaderRow(smallHeaderRequestRow);

        //request paramas
        String firstParamName = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? "NIP" : "REGON";
        String firstParam = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? reportSyncRequest.getRequestNip() : reportSyncRequest.getRequestRegon();
        String secondParamName = "NUMER KONTA BANKOWEGO";
        String secondParam = Optional.ofNullable(reportSyncRequest).map(ReportSyncRequest::getRequestBankAccount).orElse("NIE WYKORZYSTANY");
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
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell = row.createCell(60, requestDateParam);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));

        //response header
        if (null != reportSyncRequest.getGovResponseReportSync() && reportSyncRequest.getGovResponseReportSync() instanceof CheckGovResponseReportSync) {
            prepareTableCheckResponse(table, reportSyncRequest);
        } else if (null != reportSyncRequest.getGovResponseReportSync() && reportSyncRequest.getGovResponseReportSync() instanceof SearchGovResponseReportSync) {
            prepareTableSearchResponse(table, reportSyncRequest);
        }

        if (0 > 1) {
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

    private void prepareTableSearchResponse(BaseTable table, ReportSyncRequest reportSyncRequest) {
        SearchGovResponseReportSync govResponseReportSync = (SearchGovResponseReportSync) reportSyncRequest.getGovResponseReportSync();
        String reposneIdContent = String.format("Identyfikator zapytania: %s", govResponseReportSync.getRequestId());
        String veryficationDate = String.format("Weryfikacja na dzien: %s", reportSyncRequest.getReportDate().toString());

        Integer subjectReponseNumber = Optional.ofNullable(govResponseReportSync.getSubjectResponseList()).map(List::size).orElse(0);
        //todo parametry, dla ktorych nie znaleziono wynikow
        if (subjectReponseNumber > 0) {

            if (subjectReponseNumber > 1) {
                String paramsInfo = prepareEmptyParamsInfo(reportSyncRequest, govResponseReportSync.getSubjectResponseList());
                if(!paramsInfo.isEmpty()) {
                    String nonMatechParamsCell = String.format("Zapytania bez wyników: %s", paramsInfo);
                    Row<PDPage> row = table.createRow(20);
                    Cell<PDPage> cell = row.createCell(100, nonMatechParamsCell);
                    cell.setFont(PDType1Font.TIMES_ROMAN);
                    cell.setAlign(HorizontalAlignment.LEFT);
                    cell.setValign(VerticalAlignment.MIDDLE);
                    cell.setTextColor(Color.RED);
                    cell.setLineSpacing(2);
                    cell.setFontSize(12);
                }
            }

            govResponseReportSync.getSubjectResponseList().forEach(subject -> {
                String statusVAT = String.format("Status podatnik VAT: %s", subject.getStatusVat());

                prepareTableResponseHeader(table);
                prepareSearchResponseFirstLine(table, reposneIdContent, veryficationDate, statusVAT);

                //prepare search subject response BASIC rows
                // company name
                String companyName = String.format("Nazwa: %s", contentOptionalChecker(subject.getName()));
                Row<PDPage> row = table.createRow(20);
                Cell<PDPage> cell = row.createCell(100, companyName);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //company address
                String companyAdressName = String.format("Adres prowadzenia dzialalnosci: %s", contentOptionalChecker(subject.getResidenceAddress()));
                row = table.createRow(20);
                cell = row.createCell(100, companyAdressName);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //company working address
                String companyWorkingAddress = String.format("Adres siedziby: %s", contentOptionalChecker(subject.getWorkingAddress()));
                row = table.createRow(20);
                cell = row.createCell(100, companyWorkingAddress);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //nip-regon-krs
                String nip = String.format("NIP: %s", contentOptionalChecker(subject.getNip()));
                String regon = String.format("REGON: %s", contentOptionalChecker(subject.getRegon()));
                String krs = String.format("KRS: %s", contentOptionalChecker(subject.getKrs()));
                row = table.createRow(20);
                cell = row.createCell(34, nip);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(33, regon);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(33, krs);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //pesel-registerDate-virtualAccout
                String pesel = String.format("PESEL: %s", contentOptionalChecker(subject.getPesel()));
                String registerDate = String.format("Data rejestracji VAT: %s", contentOptionalChecker(subject.getRegistrationLegalDate().toString()));
                String virtualAccount = String.format("Konto wirutalne: %s", Optional.ofNullable(subject.getHasVirtualAccounts()).map(t -> t ? "TAK" : "NIE").orElse(BRAK_DANYCH));
                row = table.createRow(20);
                cell = row.createCell(30, pesel);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(40, registerDate);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(30, virtualAccount);
                cell.setFont(PDType1Font.TIMES_ROMAN);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

            });
        } else {
            prepareTableResponseHeader(table);
            prepareSearchResponseFirstLine(table, reposneIdContent, veryficationDate, "BRAK DANYCH VAT");
        }
    }

    private String prepareEmptyParamsInfo(ReportSyncRequest reportSyncRequest, List<SubjectResponse> subjectResponseList) {
        SearchGovResponseReportSync govResponseReportSync = (SearchGovResponseReportSync) reportSyncRequest.getGovResponseReportSync();
        List<String> multipleParams = Arrays.asList(Optional.ofNullable(reportSyncRequest.getRequestNip()).map(n-> n.split(","))
                .or(()->Optional.ofNullable(reportSyncRequest.getRequestRegon()).map(r-> r.split(",")))
                .or(()->Optional.ofNullable(reportSyncRequest.getRequestBankAccount()).map(b-> b.split(",")))
                .orElseThrow(()-> new RuntimeException("NO PARAMS FOUND")));
        //todo jakis exception swoj zrobic czy cos

        String nonMatechParams = multipleParams.stream()
                .map(String::trim)
                .filter(p -> subjectResponseList.stream().map(SubjectResponse::getNip).noneMatch(p::equals))
                .filter(p -> subjectResponseList.stream().map(SubjectResponse::getRegon).noneMatch(p::equals))
                .filter(p -> subjectResponseList.stream().map(resp -> Optional.ofNullable(resp.getAccountNumbersList()).orElse(new ArrayList<>())).noneMatch(list -> list.contains(p)))
                .collect(Collectors.joining(","));

        return  nonMatechParams;
    }

    private void prepareTableCheckResponse(BaseTable table, ReportSyncRequest reportSyncRequest) {

        CheckGovResponseReportSync govResponseReportSync = (CheckGovResponseReportSync) reportSyncRequest.getGovResponseReportSync();
        String reposneIdContent = String.format("Identyfikator zapytania: %s", govResponseReportSync.getRequestId());
        String statusVAT = String.format("Podatnik VAT czynny: %s", govResponseReportSync.getAccountAssigned());
        String veryficationDate = String.format("Weryfikacja na dzien: %s", reportSyncRequest.getReportDate().toString());

        //prepare response header. Same for all type reports
        prepareTableResponseHeader(table);

        Row<PDPage> row = table.createRow(20);
        Cell<PDPage> cell = row.createCell(30, reposneIdContent);
        cell.setFont(PDType1Font.TIMES_BOLD);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setFillColor(Color.YELLOW);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(30, statusVAT);

        if (statusVAT.toUpperCase().contains("TAK")) {
            cell.setTextColor(Color.GREEN);
        } else if (statusVAT.toUpperCase().contains("NIE")) {
            cell.setTextColor(Color.RED);
        }

        cell.setFont(PDType1Font.TIMES_ITALIC);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(40, veryficationDate);
        cell.setFont(PDType1Font.TIMES_ROMAN);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
    }

    private void prepareSearchResponseFirstLine(BaseTable table, String reposneIdContent, String veryficationDate, String statusVAT) {

        Row<PDPage> row = table.createRow(20);
        Cell<PDPage> cell = row.createCell(30, reposneIdContent);
        cell.setFont(PDType1Font.TIMES_BOLD);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setFillColor(Color.YELLOW);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(30, statusVAT);

        if (statusVAT.contains(StatusVat.CZYNNY.getStatusName())) {
            cell.setTextColor(Color.GREEN);
        } else if (statusVAT.contains(StatusVat.ZWOLNIONY.getStatusName())) {
            cell.setTextColor(Color.RED);
        } else if (statusVAT.contains(StatusVat.NIEZAREJESTROWANY.getStatusName())) {
            cell.setTextColor(Color.ORANGE);
        }

        cell.setFont(PDType1Font.TIMES_ITALIC);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(40, veryficationDate);
        cell.setFont(PDType1Font.TIMES_ROMAN);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
    }

    private void prepareTableResponseHeader(BaseTable table) {
        final String responseTitle = "OTRZYMANE DANE Z BIALEJ LISTY MINISTERSTWA FINANSÓW";

        Row<PDPage> smallHeaderResponseRow = table.createRow(16);
        Cell<PDPage> cell = smallHeaderResponseRow.createCell(100, responseTitle);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setFontSize(15);
        cell.setFont(PDType1Font.COURIER);
        cell.setAlign(HorizontalAlignment.CENTER);
        table.addHeaderRow(smallHeaderResponseRow);
    }

    private String contentOptionalChecker(String content) {
        return Optional.ofNullable(content).orElse(BRAK_DANYCH);
    }

}

enum StatusVat {
    CZYNNY("Czynny"),
    ZWOLNIONY("ZWolniony"),
    NIEZAREJESTROWANY("Niezarejestrowany");

    private String statusName;

    StatusVat(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
