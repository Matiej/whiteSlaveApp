package com.whiteslave.whiteslaveApp.reportSync


import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto
import com.whiteslave.whiteslaveApp.reportSync.domain.CheckGovResponse
import spock.lang.Specification
import spock.lang.Unroll

class CheckReportDto2CheckGovResponseConverterSpec extends Specification {

    private CheckReportDto checkReportDto;
    private CheckReportDto2CheckGovResponseConverter checkReportDto2CheckGovResponseConverter

    def setup() {
        checkReportDto = genrateTestObject()
        checkReportDto2CheckGovResponseConverter = new CheckReportDto2CheckGovResponseConverter();

    }

    def "should convert CheckReportDto to CheckGovResponse with all correct set fields"() {
        given:
        def expectAccountAssigned = 'TAK'
        def expectRequestId = 'aabb11'

        when: "runs method convertToCheckGovReportSync"
        CheckGovResponse resultCheckGovResponse = checkReportDto2CheckGovResponseConverter.convertToCheckGovReportSync(checkReportDto)

        then: "resultCheckGovResponse.getAccountAssigned() should be as #expectAccountAssigned"
        assert expectAccountAssigned == resultCheckGovResponse.getAccountAssigned()
        and: "resultCheckGovResponse.getRequestId() should as #expectRequestId"
        assert expectRequestId == resultCheckGovResponse.getRequestId()

    }

    private CheckReportDto genrateTestObject() {
        CheckReportDto checkReportDto = new CheckReportDto();
        checkReportDto.setAccountAssigned("TAK");
        checkReportDto.setRequestId("aabb11");
        return  checkReportDto;
    }
}