package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.GovResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.SearchGovResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.SubjectResponse;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ReportSyncService {
    private static final String REGON = "REGON";
    private static final String NIP = "NIP";
    private static final String BANK_ACCOUNT = "bankAccount";

    private final GovReportDto2ReportSyncRequestConverter govReportDto2ReportSyncRequestConverter;
    private final ArchReportService archReportService;

    public void syncToPDFAndSaveCheckReport(CheckReportDto checkReportDto, String... requestParams) {

        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.checkReportDtoConvertToReportSyncConverter(checkReportDto, requestParams);
        File file = archReportService.generateReportPdf(reportSyncRequest);
        archReportService.saveReport(reportSyncRequest);
    }

    @Transactional
    public void syncToPDFAndSaveSearchReport(SearchReportDto searchReportDto, String... requestParams) throws HibernateException {
        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.searchReportDtoConvertToReportSyncConverter(searchReportDto, requestParams);
        File file = archReportService.generateReportPdf(reportSyncRequest);
        getNegativeRequest(reportSyncRequest).forEach(archReportService::saveReport);
        archReportService.saveReport(reportSyncRequest);

    }

    //znalazienie w grupowym zapytaniu search (wiele nipow lub wiele regonow) zapytan negatywnych.
    // W response z rzadowego serwera po prostu puste parametry przychodza. Chce je zapisc do bazy jako sync odrebny.
    // Dla zapytan pojedynczych nie ma problemu.
    //todo moze jakis refaktor przeniesc do convertera aby robil pozytwyne i negatywne
    private List<ReportSyncRequest> getNegativeRequest(ReportSyncRequest reportSyncRequest) {
        List<ReportSyncRequest> negativeList = new ArrayList<>();
        SearchGovResponse govResponseReportSync = (SearchGovResponse) reportSyncRequest.getGovResponse();
        List<SubjectResponse> subjectResponseList = govResponseReportSync.getSubjectResponseList();

        if (null != subjectResponseList && subjectResponseList.size() > 1) {
            if (null != reportSyncRequest.getRequestNip()) {
                String requestedParams = reportSyncRequest.getRequestNip();
                Arrays.stream(requestedParams.split(",")).forEach(param -> {
                    if (!subjectResponseList.stream().map(SubjectResponse::getNip).collect(Collectors.joining(",")).contains(param)) {
                        negativeList.add(generateReportSync(NIP, param, reportSyncRequest));
                    }
                });
            }
            if (null != reportSyncRequest.getRequestRegon()) {
                Arrays.stream(reportSyncRequest.getRequestRegon().split(",")).forEach(param -> {
                    if (!subjectResponseList.stream().map(SubjectResponse::getRegon).collect(Collectors.joining(",")).contains(param)) {
                        negativeList.add(generateReportSync(REGON, param, reportSyncRequest));
                    }
                });
            }

            if (null != reportSyncRequest.getRequestBankAccount()) {
                Arrays.stream(reportSyncRequest.getRequestBankAccount().split(",")).forEach(param -> {
                    if (!subjectResponseList.stream()
                            .map(SubjectResponse::getAccountNumbersList)
                            .map(s -> String.join(",", s))
                            .collect(Collectors.joining(",")).contains(param)) {
                        negativeList.add(generateReportSync(BANK_ACCOUNT, param, reportSyncRequest));
                    }
                });
            }
        }
        return negativeList;
    }

    private ReportSyncRequest generateReportSync(String paramType, String
            params, ReportSyncRequest reportToupdate) {

        SearchGovResponse govResponseReportSync = (SearchGovResponse) reportToupdate.getGovResponse();
        GovResponse negativGovResponseReportySync = new SearchGovResponse(
                    govResponseReportSync.getRequestId(),
                    new ArrayList<>());

        ReportSyncRequest rsr =ReportSyncRequest.builder()
                .reportDate(reportToupdate.getReportDate())
                .reportType(reportToupdate.getReportType())
                .requestBankAccount(reportToupdate.getRequestBankAccount())
                .requestDate(reportToupdate.getRequestDate())
                .requestNip(reportToupdate.getRequestNip())
                .requestRegon(reportToupdate.getRequestRegon())
                .searchResult(SearchResult.NEGATIVE)
                .govResponse(negativGovResponseReportySync)
                .build();

        switch (paramType) {
            case REGON:
                rsr.setRequestRegon(params);
                break;
            case NIP:
                rsr.setRequestNip(params);
                break;
            case BANK_ACCOUNT:
                rsr.setRequestBankAccount(params);
        }
        return rsr;
    }
}



