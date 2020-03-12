package com.whiteslave.whiteslaveApp.reportSync;

import com.whiteslave.whiteslaveApp.archiveReport.ArchReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ReportSyncServiceImpl implements ReportSyncService {

    private final GovReportDto2ReportSyncRequestConverter govReportDto2ReportSyncRequestConverter;
//todo BIG zrobić tutaj serwis z arch to będzie miało archAndSave jedną metodą i będzie zapisywać i robić pdf-> musi byc nierozlaczne bo kazdy zapis do bazy ma miec pdf
// todo nastepna bedzie jakis emailReportService i bedzie toto wysylac maile moze odddzielnie byc lub w paczce sam nie wiem. Na pewno odrebny watek. .
    private final ArchReportService archReportService;
    @Override
    public void syncToPDFAndSaveCheckReport(CheckReportDto checkReportDto, String...requestParams) {

        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.checkReportDtoConvertToReportSyncConverter(checkReportDto, requestParams);
        archReportService.saveReport(reportSyncRequest);
//        ReportSyncRequestEntity reportSyncRequestEntity = entityConverter.convert2Entity(reportSyncRequest);
//        repository.save(reportSyncRequestEntity);
    }

    @Override
    public void syncToPDFAndSaveSearchReport(SearchReportDto searchReportDto, String... requestParams) throws HibernateException {
        ReportSyncRequest reportSyncRequest = govReportDto2ReportSyncRequestConverter.searchReportDtoConvertToReportSyncConverter(searchReportDto, requestParams);
        archReportService.saveReport(reportSyncRequest);
//        ReportSyncRequestEntity reportSyncRequestEntity = entityConverter.convert2Entity(reportSyncRequest);
//        repository.save(reportSyncRequestEntity);
    }

}
