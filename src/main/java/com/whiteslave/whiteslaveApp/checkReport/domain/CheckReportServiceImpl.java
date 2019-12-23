package com.whiteslave.whiteslaveApp.checkReport.domain;

import com.whiteslave.whiteslaveApp.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.client.dto.MfGovCheckResource;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CheckReportServiceImpl implements CheckReportService {

    private MfGovWhiteListClient mfGovWhiteListClient;
    private MfGovCheck2CheckReportDtoConverter converter;

    @Override
    public CheckReportDto checkByNipAndBankAccoutAndDate(String nip, String bankAccount, String date) {
        MfGovCheckResource mfGovCheckResource = mfGovWhiteListClient.checkByNipAndBankAccoutAndDate(nip, bankAccount, date);
        return converter.convertMfGovCheck2CheckReportDto(mfGovCheckResource);
    }

    @Override
    public CheckReportDto checkByRegonAndBankAccoutnAndDate(String regon, String bankAccount, String date) {
        MfGovCheckResource mfGovCheckResource = mfGovWhiteListClient.checkByRegonAndBankAccoutnAndDate(regon, bankAccount, date);
        return converter.convertMfGovCheck2CheckReportDto(mfGovCheckResource);
    }
}
