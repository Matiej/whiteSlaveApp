package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
class GovReportDto2ReportSyncRequestConverter {

    private final CheckReportDto2CheckGovReportSyncConverter checkGovReportSyncConverter;
    private final SearchReportDto2SearchGovReportSyncConverter searchGovReportSyncConverter;
    private final static String DATE = "DATE";
    private final static String NIP = "NIP";
    private final static String REGON = "REGON";
    private final static String BANK_ACCOUNT = "BANKACCOUNT";

    GovReportDto2ReportSyncRequestConverter(CheckReportDto2CheckGovReportSyncConverter checkGovReportSyncConverter, SearchReportDto2SearchGovReportSyncConverter searchGovReportSyncConverter) {
        this.checkGovReportSyncConverter = checkGovReportSyncConverter;
        this.searchGovReportSyncConverter = searchGovReportSyncConverter;
    }

    public ReportSyncRequest checkReportDtoConvertToReportSyncConverter(CheckReportDto checkReportDto, String[] requestParams) {
        Map<String, String> params = prepareParams(requestParams);
        return ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.parse(params.get(DATE)))
                .searchResult(checkReportDto.getAccountAssigned().equals("TAK") ? SearchResult.POSITIVE : SearchResult.NEGATIVE)
                .reportType(ReportType.CHECK)
                .govResponseReportSync(checkGovReportSyncConverter.convertToCheckGovReportSync(checkReportDto))
                .requestNip(params.get(NIP))
                .requestRegon(params.get(REGON))
                .requestBankAccount(params.get(BANK_ACCOUNT))
                .build();
    }

    public ReportSyncRequest searchReportDtoConvertToReportSyncConverter(SearchReportDto searchReportDto, String[] requestParams) {
        Map<String, String> params = prepareParams(requestParams);

        return ReportSyncRequest.builder()
                .requestDate(LocalDateTime.now().withNano(0))
                .reportDate(LocalDate.parse(params.get(DATE)))
                .searchResult(searchReportDto.getSubjectDtoList().size()>0 ? SearchResult.POSITIVE : SearchResult.NEGATIVE)
                .reportType(ReportType.SEARCH)
                .govResponseReportSync(searchGovReportSyncConverter.convertToSearchGovResponseReportSync(searchReportDto))
                .requestNip(params.get(NIP))
                .requestRegon(params.get(REGON))
                .requestBankAccount(params.get(BANK_ACCOUNT))
                .build();
    }


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
