package com.whiteslave.whiteslaveApp.reportSync

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponse
import com.whiteslave.whiteslaveApp.reportSync.domain.ReportSyncRequest
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType
import com.whiteslave.whiteslaveApp.reportSync.domain.enums.SearchResult
import spock.lang.Specification

class GovReportDto2ReportSyncRequestConverterSpec extends Specification {

    def checkGovReportSyncConverter = Mock(CheckReportDto2CheckGovResponseConverter)
    def searchGovReportSyncConverter = Mock(SearchReportDto2SearchGovResponseConverter)

    def govReportDto2ReportSyncRequestConverter = new GovReportDto2ReportSyncRequestConverter(checkGovReportSyncConverter, searchGovReportSyncConverter)

    def setupSpec() {

    }

    def setup() {
    }

    def "should convert 2 ReportSyncRequest with all params and positive result"() {
        given: "prepare test data"
        def date = "2020-01-01"
        def regon = "095498894"
        def bankAccountNo = "03174000197837788874275129"
        def nip = "9326375670"
        def params = [date,regon,bankAccountNo,nip] as String[]
        def checkReportDto = new CheckReportDto( "TAK", "aabb22")
        checkGovReportSyncConverter.convertToCheckGovReportSync(checkReportDto) >> new CheckGovResponse("aabb22", "TAK")
        when:
        def result = govReportDto2ReportSyncRequestConverter.checkReportDtoConvertToReportSyncConverter(checkReportDto,params) as ReportSyncRequest

        then:
        assert result.getSearchResult() == SearchResult.POSITIVE
        assert result.getRequestNip() == nip
        assert result.getRequestRegon() == regon
        assert result.getGovResponse().getRequestId() == 'aabb22'
        assert result.reportType == ReportType.CHECK
        assert result.getRequestBankAccount() == bankAccountNo
        assert result.getReportDate().toString() == date

    }


}