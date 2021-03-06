package com.whiteslave.whiteslaveApp.archiveReport.pdfReport;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.image.Image;
import be.quodlibet.boxable.line.LineStyle;
import com.google.common.collect.Lists;
import com.whiteslave.whiteslaveApp.reportSync.domain.*;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
class PdfTableService {
//todo refactor. Zbudowanie fabryki i abstrakci dla check i search. 
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

        PDType0Font polishFont = PDType0Font.load(document, new File(Objects.requireNonNull(getClass().getClassLoader().getResource("arial.ttf")).getFile()));

        BaseTable table = new BaseTable(yLastPos, yStartNewPage,
                bottomMargin, tableWidth, margin, document, page, true, drawContent);

        prepareTableHeader(table, image, reportVerResult);
        prepareTableRequest(table, reportSyncRequest, polishFont);

        //response header
        if (null != reportSyncRequest.getGovResponse() && reportSyncRequest.getGovResponse() instanceof CheckGovResponse) {
            prepareTableCheckResponse(table, reportSyncRequest, polishFont);
        } else if (null != reportSyncRequest.getGovResponse() && reportSyncRequest.getGovResponse() instanceof SearchGovResponse) {
            prepareTableSearchResponse(table, reportSyncRequest, polishFont);
        }
        return table;
    }

    private void prepareTableHeader(BaseTable table, BufferedImage image, String reportVerResult) {

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

    }

    private void prepareTableRequest(BaseTable table, ReportSyncRequest reportSyncRequest, PDType0Font polishFont) {
        //header row for request params
        Row<PDPage> row = table.createRow(16);
        Cell<PDPage> cell = row.createCell(100, "PRZEKAZANE PARAMETRY ZAPYTANIA");
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell.setFontSize(15);
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.CENTER);

        //request paramas
        String firstParamName = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? "NIP" : "REGON";
        String firstParam = Optional.ofNullable(reportSyncRequest.getRequestNip()).isPresent() ? reportSyncRequest.getRequestNip() : reportSyncRequest.getRequestRegon();
        String secondParamName = "NUMER KONTA BANKOWEGO";
        String secondParam = Optional.of(reportSyncRequest).map(ReportSyncRequest::getRequestBankAccount).orElse("NIE WYKORZYSTANY");
        String firstParamCell = firstParamName + ": " + firstParam;
        String secondParamCell = secondParamName + ": " + secondParam;

        row = table.createRow(20);
        cell = row.createCell(40, firstParamCell);
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(60, secondParamCell);
        cell.setFont(polishFont);
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
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));
        cell = row.createCell(60, requestDateParam);
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell.setBottomBorderStyle(new LineStyle(Color.GRAY, 3));

    }

    private void prepareTableCheckResponse(BaseTable table, ReportSyncRequest reportSyncRequest, PDType0Font polishFont) {

        CheckGovResponse govResponseReportSync = (CheckGovResponse) reportSyncRequest.getGovResponse();
        String reposneIdContent = String.format("Identyfikator zapytania: %s", govResponseReportSync.getRequestId());
        String statusVAT = String.format("Podatnik VAT czynny: %s", govResponseReportSync.getAccountAssigned());
        String veryficationDate = String.format("Weryfikacja na dzien: %s", reportSyncRequest.getReportDate().toString());

        //prepare response header. Same for all type reports
        prepareTableResponseHeader(table);

        Row<PDPage> row = table.createRow(20);
        Cell<PDPage> cell = row.createCell(30, reposneIdContent);
        cell.setFont(polishFont);
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

        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(40, veryficationDate);
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
    }

    private void prepareTableSearchResponse(BaseTable table, ReportSyncRequest reportSyncRequest, PDType0Font polishFont) {
        SearchGovResponse govResponseReportSync = (SearchGovResponse) reportSyncRequest.getGovResponse();
        String reposneIdContent = String.format("Identyfikator zapytania: %s", govResponseReportSync.getRequestId());
        String veryficationDate = String.format("Weryfikacja na dzien: %s", reportSyncRequest.getReportDate().toString());

        Integer subjectReponseNumber = Optional.ofNullable(govResponseReportSync.getSubjectResponseList()).map(List::size).orElse(0);
        if (subjectReponseNumber > 0) {
            try {
                String paramsInfo = prepareEmptyParamsInfo(reportSyncRequest, govResponseReportSync.getSubjectResponseList());
                if (!paramsInfo.isEmpty()) {
                    String nonMatechParamsCell = String.format("Zapytania bez wyników: %s", paramsInfo);
                    Row<PDPage> row = table.createRow(20);
                    Cell<PDPage> cell = row.createCell(100, nonMatechParamsCell);
                    cell.setFont(polishFont);
                    cell.setAlign(HorizontalAlignment.LEFT);
                    cell.setValign(VerticalAlignment.MIDDLE);
                    cell.setTextColor(Color.RED);
                    cell.setLineSpacing(2);
                    cell.setFontSize(12);
                }
            }catch (Exception e) {
                log.error(e.getMessage());
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
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //company address
                String companyAdressName = String.format("Adres prowadzenia działalnośsci  albo adres miejsca zamieszkania, " +
                        "w przypadku braku adresu stałego miejsca prowadzenia działalności " +
                        "- w odniesieniu do osoby fizycznej: %s", contentOptionalChecker(subject.getResidenceAddress()));
                row = table.createRow(20);
                cell = row.createCell(100, companyAdressName);
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //company working address
                String companyWorkingAddress = String.format("Adres siedziby w przypadku podmiotu niebędącego osobą fizyczną: %s", contentOptionalChecker(subject.getWorkingAddress()));
                row = table.createRow(20);
                cell = row.createCell(100, companyWorkingAddress);
                cell.setFont(polishFont);
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
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(33, regon);
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(33, krs);
                cell.setFont(polishFont);
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
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(40, registerDate);
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);
                cell = row.createCell(30, virtualAccount);
                cell.setFont(polishFont);
                cell.setAlign(HorizontalAlignment.LEFT);
                cell.setValign(VerticalAlignment.MIDDLE);
                cell.setLineSpacing(2);
                cell.setFontSize(12);

                //bank accounts
                int maxAccountOnPage = 30;
                List<List<String>> accountPartition = Lists.partition(Optional.ofNullable(subject.getAccountNumbersList()).orElse(new ArrayList<>())
                        , maxAccountOnPage);
                accountPartition.forEach(list -> {
                    String accounts = String.join("; ", list);
                    String bankAccounts = String.format("Numery rachunków rozliczeniowych lub imiennych rachunków w SKOK: %s",
                            accounts.isEmpty() ? BRAK_DANYCH : accounts);

                    Row<PDPage> bankRow = table.createRow(20);
                    Cell<PDPage> bankCell = bankRow.createCell(100, bankAccounts);
                    bankCell.setFont(polishFont);
                    bankCell.setAlign(HorizontalAlignment.LEFT);
                    bankCell.setValign(VerticalAlignment.MIDDLE);
                    bankCell.setLineSpacing(2);
                    bankCell.setFontSize(12);

                });

                //representatives
                if (!subject.getRepresentativesResponseList().isEmpty()) {
                    String representativesHeader = "Osoby wchodzace w sklad organu uprawnionego do reprezentowania";
                    prepareReposneListTable(table, subject.getRepresentativesResponseList(), representativesHeader, polishFont);
                }

                //authorizedClerks
                if (!subject.getAuthorizedClerksResponseList().isEmpty()) {
                    String authorizedClearks = "Imiona i nazwiska prokurentów oraz ich numery identyfikacji podatkowej lub numery PESEL";
                    prepareReposneListTable(table, subject.getAuthorizedClerksResponseList(), authorizedClearks, polishFont);
                }

                //partners
                if (!subject.getPartnersResponseList().isEmpty()) {
                    String partners = "Imie i nazwisko lub firma (nazwa) wspólnika oraz jego numer identyfikacji podatkowej lub numer PESEL";
                    prepareReposneListTable(table, subject.getPartnersResponseList(), partners, polishFont);
                }

                //other vat date header
                if (null != subject.getRegistrationDenialDate() || null != subject.getRemovalDate() || null != subject.getRestorationDate()) {
                    String otherVatDate = "Dodatkowe dane rejestracyjne VAT";
                    prepareOhterVatHeader(table, otherVatDate);
                }

                if (null != subject.getRegistrationDenialDate()) {
                    String registrationDenialDate = String.format("Data odmowy rejestracji VAT: %s", subject.getRegistrationDenialDate().toString());
                    String registrationDenialBasis = String.format("Podstawa prawna odmowy rejestracji: %s", subject.getRegistrationDenialBasis());
                    prepareVatTable(table, registrationDenialDate, registrationDenialBasis);
                }

                if (null != subject.getRemovalDate()) {
                    String removalDate = String.format("Data wykreslenia rejestracji jako podatnika VAT: %s", subject.getRemovalDate().toString());
                    String removalBasis = String.format("Podstawa prawna wykreslenia: %s", subject.getRemovalBasis());
                    prepareVatTable(table, removalDate, removalBasis);
                }

                if (null != subject.getRestorationDate()) {
                    String restorationDate = String.format("Data przywrócenia rejestracji jako podatnika VAT: %s", subject.getRestorationDate().toString());
                    String restorationBasis = String.format("Podstawa prawna przywrócenia: %s", subject.getRestorationBasis());
                    prepareVatTable(table, restorationDate, restorationBasis);
                }

            });
        } else {
            prepareTableResponseHeader(table);
            prepareSearchResponseFirstLine(table, reposneIdContent, veryficationDate, "BRAK DANYCH VAT");
        }
    }
    private String prepareEmptyParamsInfo(ReportSyncRequest reportSyncRequest, List<SubjectResponse> subjectResponseList) throws Exception {
        List<String> multipleParams = Arrays.asList(Optional.ofNullable(reportSyncRequest.getRequestNip()).map(n -> n.split(","))
                .or(() -> Optional.ofNullable(reportSyncRequest.getRequestRegon()).map(r -> r.split(",")))
                .or(() -> Optional.ofNullable(reportSyncRequest.getRequestBankAccount()).map(b -> b.split(",")))
                .orElseThrow(() -> new Exception(String.format("Can not prepare PDF report. No request params found for id: %s",
                        reportSyncRequest.getGovResponse().getRequestId()))));
        return multipleParams.stream()
                .map(String::trim)
                .filter(p -> subjectResponseList.stream().map(SubjectResponse::getNip).noneMatch(p::equals))
                .filter(p -> subjectResponseList.stream().map(SubjectResponse::getRegon).noneMatch(p::equals))
                .filter(p -> subjectResponseList.stream().map(resp -> Optional.ofNullable(resp.getAccountNumbersList()).orElse(new ArrayList<>())).noneMatch(list -> list.contains(p)))
                .collect(Collectors.joining(","));
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
            cell.setFillColor(Color.GRAY);
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
    }

    private String contentOptionalChecker(String content) {
        return Optional.ofNullable(content).orElse(BRAK_DANYCH);
    }

    private void prepareReposneListTable(BaseTable table, List<CompanyPersons> companyPersonsList, String header, PDType0Font polishFont) {
        Row<PDPage> row = table.createRow(15);
        Cell<PDPage> cell = row.createCell(100, header);
        cell.setFont(polishFont);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setFontSize(13);
        companyPersonsList.forEach(representative -> {
            //company name
            String reprCompanyName = String.format("Nazwa: %s", contentOptionalChecker(representative.getCompanyName()));
            Row<PDPage> persRow = table.createRow(20);
            Cell<PDPage> persCell = persRow.createCell(100, reprCompanyName);
            persCell.setFont(polishFont);
            persCell.setAlign(HorizontalAlignment.LEFT);
            persCell.setValign(VerticalAlignment.MIDDLE);
            persCell.setLineSpacing(2);
            persCell.setFontSize(12);
            // name lastname
            String name = String.format("Imie: %s", contentOptionalChecker(representative.getFirstName()));
            persRow = table.createRow(20);
            persCell = persRow.createCell(50, name);
            persCell.setFont(polishFont);
            persCell.setAlign(HorizontalAlignment.LEFT);
            persCell.setValign(VerticalAlignment.MIDDLE);
            persCell.setLineSpacing(2);
            persCell.setFontSize(12);
            String lastName = String.format("Nazwisko: %s", contentOptionalChecker(representative.getLastName()));
            persCell = persRow.createCell(50, lastName);
            persCell.setFont(polishFont);
            persCell.setAlign(HorizontalAlignment.LEFT);
            persCell.setValign(VerticalAlignment.MIDDLE);
            persCell.setLineSpacing(2);
            persCell.setFontSize(12);
            //nip pesel
            String pesel = String.format("PESEL: %s", contentOptionalChecker(representative.getLastName()));
            persRow = table.createRow(20);
            persCell = persRow.createCell(50, pesel);
            persCell.setFont(polishFont);
            persCell.setAlign(HorizontalAlignment.LEFT);
            persCell.setValign(VerticalAlignment.MIDDLE);
            persCell.setLineSpacing(2);
            persCell.setFontSize(12);
            String nip = String.format("NIP: %s", contentOptionalChecker(representative.getNip()));
            persCell = persRow.createCell(50, nip);
            persCell.setFont(polishFont);
            persCell.setAlign(HorizontalAlignment.LEFT);
            persCell.setValign(VerticalAlignment.MIDDLE);
            persCell.setLineSpacing(2);
            persCell.setFontSize(12);
        });
    }

    private void prepareOhterVatHeader(BaseTable table, String otherVatDate) {
        Row<PDPage> row = table.createRow(15);
        Cell<PDPage> cell = row.createCell(100, otherVatDate);
        cell.setFont(PDType1Font.TIMES_BOLD);
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setFontSize(13);
    }

    private void prepareVatTable(BaseTable table, String date, String basis) {
        Row<PDPage> row = table.createRow(20);
        Cell<PDPage> cell = row.createCell(50, date);
        cell.setFont(PDType1Font.TIMES_ROMAN);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
        cell = row.createCell(50, basis);
        cell.setFont(PDType1Font.TIMES_ROMAN);
        cell.setAlign(HorizontalAlignment.LEFT);
        cell.setValign(VerticalAlignment.MIDDLE);
        cell.setLineSpacing(2);
        cell.setFontSize(12);
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
