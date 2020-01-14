package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import com.whiteslave.whiteslaveApp.reportSync.entity.ReportSyncRequestEntity;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
class ReportSyncServiceImpl implements ReportSyncService {

    private final CheckReportDto2CheckGovReportSyncConverter checkGovReportSyncConverter;
    private final SearchReportDto2SearchGovReportSyncConverter searchGovReportSyncConverter;
    private final ReportSyncRequest2EntityConverter entityConverter;
    private final ReportSyncRequestEntityRepository repository;

    @Override
    public void syncAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams) {
        Map<String, String> params = prepareParams(requestParams);
        //todo zrobić plik PDF i zapisać jego dane
        ReportSyncRequest reportSyncRequest = ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.parse(params.get("DATE")))
                .pdfFileName("some.pdf")
                .searchResult(checkReportDto.getAccountAssigned().equals("TAK") ? SearchResult.POSITIVE : SearchResult.NEGATIVE)
                .reportType(ReportType.CHECK)
                .govResponseReportSync(checkGovReportSyncConverter.convertToCheckGovReportSync(checkReportDto))
                .requestNip(params.get("NIP"))
                .requestRegon(params.get("REGON"))
                .requestBankAccount(params.get("BANKACCOUNT"))
                .build();
        ReportSyncRequestEntity reportSyncRequestEntity = entityConverter.convert2Entity(reportSyncRequest);
        repository.save(reportSyncRequestEntity);
        //create and save pdf file
        //create and send emailAdress
        //convert to entity
        //save entity to data base

    }

    @Override
    public void syncAndSaveSearchReport(SearchReportDto searchReportDto, String... requestParams) {
        Map<String, String> params = prepareParams(requestParams);
        ReportSyncRequest reportSyncRequest = ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.parse(params.get("DATE")))
                .pdfFileName("some.pdf")
                .searchResult(searchReportDto.getSubjectDtoList().size()>0 ? SearchResult.POSITIVE : SearchResult.NEGATIVE)
                .reportType(ReportType.SEARCH)
                .govResponseReportSync(searchGovReportSyncConverter.convertToSearchGovResponseReportSync(searchReportDto))
                .requestNip(params.get("NIP"))
                .requestRegon(params.get("REGON"))
                .requestBankAccount(params.get("BANKACCOUNT"))
                .build();
        ReportSyncRequestEntity reportSyncRequestEntity = entityConverter.convert2Entity(reportSyncRequest);
        repository.save(reportSyncRequestEntity);
    }

    //todo kurwa jaka tragedia. Cos tym trza zrobic.
    private Map<String, String> prepareParams(String... requestParams) {
        Map<String, String> requestParamsMap = new HashMap<>();
        Arrays.stream(requestParams).forEach(param -> {
            if (param.length() == 9 || param.length() == 14) {
                requestParamsMap.put("REGON", param);
            } else if (param.length() == 26) {
                requestParamsMap.put("BANKACCOUNT", param);
            } else if (param.length() == 10 && param.matches("[0-9]+")) {
                requestParamsMap.put("NIP", param);
            } else {
                requestParamsMap.put("DATE", param);
            }
        });
        return requestParamsMap;
    }

}
