package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.CheckReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.SearchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class ReportDtoFacadeImpl implements ReportDtoFacade {

    private final SearchReportService searchReportService;
    private final CheckReportService checkReportService;
    private final ReportSyncService reportSyncService;

    @Override
    public CheckReportDto checkAndSynchronizeByNipAndBankAccoutAndDate(String nip, String bankAccount, String date) {
        String preparedBankAccount = checkAndPrepareSignleValue(bankAccount);
        String preparedNip = checkAndPrepareSignleValue(nip);
        CheckReportDto checkReportDto = checkReportService.checkByNipAndBankAccoutAndDate(preparedNip, preparedBankAccount, date);
        reportSyncService.syncToPDFAndSaveCheckReport(checkReportDto, preparedNip, preparedBankAccount, date);
        return checkReportDto;
    }

    @Override
    public CheckReportDto checkAmdSynchronizeByRegonAndBankAccoutnAndDate(String regon, String bankAccount, String date) {
        String preparedBankAccount = checkAndPrepareSignleValue(bankAccount);
        String preparedReqgon = checkAndPrepareSignleValue(regon);
        CheckReportDto checkReportDto = checkReportService.checkByRegonAndBankAccoutnAndDate(preparedReqgon, preparedBankAccount, date);
        reportSyncService.syncToPDFAndSaveCheckReport(checkReportDto, preparedReqgon, preparedBankAccount, date);
        return checkReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountAndDate(String bankAccount, String date) {
        String preparedBankAccount = checkAndPrepareSignleValue(bankAccount);
        SearchReportDto searchReportDto = searchReportService.searchByBankAccountAndDate(preparedBankAccount, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto,preparedBankAccount, date);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByBankAccountsAndDate(String bankAccounts, String date) {
        String prepareMultiplePValues = checkAndPrepareMultiplePValues(bankAccounts);
        SearchReportDto searchReportDto = searchReportService.searchByBankAccountsAndDate(prepareMultiplePValues, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto, prepareMultiplePValues, date);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByNipAndDate(String nip, String date) {
        String prepareSignleValue = checkAndPrepareSignleValue(nip);
        SearchReportDto searchReportDto = searchReportService.searchByNipAndDate(prepareSignleValue, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto, prepareSignleValue, date);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByNipsAndDate(String nips, String date) {
        String prepareMultiplePValues = checkAndPrepareMultiplePValues(nips);
        SearchReportDto searchReportDto = searchReportService.searchByNipsAndDate(prepareMultiplePValues, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto, prepareMultiplePValues, date);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByRegonAndDate(String regon, String date) {
        String prepareSignleValue = checkAndPrepareSignleValue(regon);
        SearchReportDto searchReportDto = searchReportService.searchByRegonAndDate(prepareSignleValue, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto, prepareSignleValue, date);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchAndSynchronizeByRegonsAndDate(String regons, String date) {
        String multiplePValues = checkAndPrepareMultiplePValues(regons);
        SearchReportDto searchReportDto = searchReportService.searchByRegonsAndDate(multiplePValues, date);
        reportSyncService.syncToPDFAndSaveSearchReport(searchReportDto, multiplePValues, date);
        return searchReportDto;
    }

    private String checkAndPrepareSignleValue(String singleValue) {
        return singleValue.strip().replaceAll("-", "").replaceAll(" ", "");
    }

    private String checkAndPrepareMultiplePValues(String multipleVal) {
        final String valueSeparator = ",";
        List<String> stringList = Arrays.asList(checkAndPrepareSignleValue(multipleVal).strip().split(valueSeparator));
        return stringList.stream()
                .map(String::trim)
                .filter(trimedVal -> !trimedVal.isEmpty() || !trimedVal.isBlank())
                .collect(Collectors.joining(valueSeparator));
    }

}
