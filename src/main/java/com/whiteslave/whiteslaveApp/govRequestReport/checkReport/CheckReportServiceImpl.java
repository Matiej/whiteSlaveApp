package com.whiteslave.whiteslaveApp.govRequestReport.checkReport;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.MfGovCheckResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CheckReportServiceImpl implements CheckReportService {

    private final MfGovWhiteListClient mfGovWhiteListClient;
    private final MfGovCheck2CheckReportDtoConverter converter;

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
