package com.whiteslave.whiteslaveApp.searchReport.domain;

import com.whiteslave.whiteslaveApp.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.client.dto.MfGovSearchResource;
import com.whiteslave.whiteslaveApp.client.dto.MfGovSearchResources;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@RequiredArgsConstructor
class SearchReportServiceImpl implements SearchReportService {

    private final MfGovWhiteListClient mfGovWhiteListClient;
    private final MfGovSearch2SearchReportDtoConventer conventer;

    @Override
    public SearchReportDto searchByBankAccountAndDate(String bankAccount, String date) {
        MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByBankAccountAndDate(bankAccount, date);
        SearchReportDto searchReportDto = conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
        return searchReportDto;
    }

    @Override
    public SearchReportDto searchByBankAccountsAndDate(String bankAccounts, String date) {
        MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByBankAccountsAndDate(bankAccounts, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

    @Override
    public SearchReportDto searchByNipAndDate(String nip, String date) throws ValidationException {
        MfGovSearchResource mfGovSearchResource = mfGovWhiteListClient.searchByNipAndDate(nip, date);
        return conventer.convertMfSearchResource2SearchReportDto(mfGovSearchResource);
    }

    @Override
    public SearchReportDto searchByNipsAndDate(String nips, String date) {
        MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByNipsAndDate(nips, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

    @Override
    public SearchReportDto searchByRegonAndDate(String regon, String date) {
        MfGovSearchResource mfGovSearchResource = mfGovWhiteListClient.searchByRegonAndDate(regon, date);
        return conventer.convertMfSearchResource2SearchReportDto(mfGovSearchResource);
    }

    @Override
    public SearchReportDto searchByRegonsAndDate(String regons, String date) {
        MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByRegonsAndDate(regons, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

//    private String checkAndPrepareSignleValue(String singleValue) {
//        return singleValue.strip().replaceAll("-", "");
//    }
//
//    private String checkAndPrepareMultiplePValues(String multipleVal) {
//        final String valueSeparator = ",";
//        List<String> stringList = Arrays.asList(multipleVal.strip().replaceAll("-", "").split(valueSeparator));
//        return stringList.stream()
//                .map(t-> t.trim())
//                .filter(trimedVal-> !trimedVal.isEmpty() || !trimedVal.isBlank())
//                .collect(Collectors.joining(valueSeparator));
//    }
}
