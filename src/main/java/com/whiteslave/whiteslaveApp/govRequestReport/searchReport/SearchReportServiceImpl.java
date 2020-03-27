package com.whiteslave.whiteslaveApp.govRequestReport.searchReport;

import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.MfGovSearchResource;
import com.whiteslave.whiteslaveApp.govRequestReport.client.dto.MfGovSearchResources;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SearchReportServiceImpl implements SearchReportService {

    private final MfGovWhiteListClient mfGovWhiteListClient;
    private final MfGovSearch2SearchReportDtoConventer conventer;

    @Override
    public SearchReportDto searchByBankAccountAndDate(final String bankAccount, final String date) {
        final MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByBankAccountAndDate(bankAccount, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

    @Override
    public SearchReportDto searchByBankAccountsAndDate(final String bankAccounts,final String date) {
       final MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByBankAccountsAndDate(bankAccounts, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

    @Override
    public SearchReportDto searchByNipAndDate(final String nip, final String date) {
         final MfGovSearchResource mfGovSearchResource = mfGovWhiteListClient.searchByNipAndDate(nip, date);
        return conventer.convertMfSearchResource2SearchReportDto(mfGovSearchResource);
    }

    @Override
    public SearchReportDto searchByNipsAndDate(final String nips, final String date) {
        final MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByNipsAndDate(nips, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

    @Override
    public SearchReportDto searchByRegonAndDate(final String regon, final String date) {
        final MfGovSearchResource mfGovSearchResource = mfGovWhiteListClient.searchByRegonAndDate(regon, date);
        return conventer.convertMfSearchResource2SearchReportDto(mfGovSearchResource);
    }

    @Override
    public SearchReportDto searchByRegonsAndDate(final String regons, final String date) {
        final MfGovSearchResources mfGovSearchResources = mfGovWhiteListClient.searchByRegonsAndDate(regons, date);
        return conventer.convertMfSearchResources2SearchReportDto(mfGovSearchResources);
    }

}
