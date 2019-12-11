package com.whiteslave.whiteslaveApp.searchReport.domain;

import com.whiteslave.whiteslaveApp.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.client.dto.MfGovSearchResources;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SearchReportServiceImpl implements SearchReportService{

    private final MfGovWhiteListClient mfGovWhiteListClient;
    private final MfGovSearch2SearchReportDtoConventer conventer;

    @Override
    public SearchReportDto searchByBankAccountAndDate(String bankAccount, String date) {
        MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByBankAccountAndDate(bankAccount, date);
        SearchReportDto searchReportDto = conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
        return searchReportDto;
    }
}
