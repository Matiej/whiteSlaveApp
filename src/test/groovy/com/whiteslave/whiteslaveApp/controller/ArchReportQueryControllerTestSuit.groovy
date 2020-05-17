package com.whiteslave.whiteslaveApp.controller

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.ArchReportQueryFacade
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView
import com.whiteslave.whiteslaveApp.exceptionHandler.WhiteSlaveExceptionHandler
import feign.FeignException
import org.hibernate.HibernateException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ArchReportQueryControllerTestSuit extends Specification {

    protected MockMvc mvc

    def archFacade = Mock(ArchReportQueryFacade)

    def archReportQueryController = new ArchReportQueryController(archFacade)

    @Shared
    List<CheckReportQueryView> reportQueryViewList;

    def setup() {
        mvc = MockMvcBuilders.standaloneSetup(new ArchReportQueryController(archFacade))
                .setControllerAdvice(new WhiteSlaveExceptionHandler())
                .build()
    }

    def setupSpec() {
        //todo przygotować listę
        reportQueryViewList = new ArrayList<>()
    }

    def "should return list of checkReportQueryView and HTTP status code OK"() {
        given:
        archFacade.allCheckReports() >> reportQueryViewList

        when:
        ResponseEntity<List<CheckReportQueryView>> result = archReportQueryController.findAllSyncCheckReports()

        and:
        def response = mvc.perform(get("/reportquery/checkSyncReports"))

        then:
        result != null
        assert result.statusCode == HttpStatus.OK

        and:
        response.andExpect(status().isOk())

    }

    def "should handle HibernateException thrown from findAllSyncCheckReports() and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Data Base server error. Can not get or save any report."

        and:
        archFacade.allCheckReports() >> { List<CheckReportQueryView> reportQueryViewList ->
            throw new HibernateException(messageDetails)
        }

        when:
        def response = mvc.perform(get("/reportquery/checkSyncReports"))

        and:
        archReportQueryController.findAllSyncCheckReports()

        then:
        def e = thrown(HibernateException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle FeignException thrown from findAllSyncCheckReports() and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from Feign library."
        def handledMessage = "Gov resource server error. Can not send and receive any report"
        def feignStatus = 503

        and:
        archFacade.allCheckReports() >> { List<CheckReportQueryView> reportQueryViewList ->
            throw new FeignException(feignStatus, messageDetails)
        }
        when:
        def response = mvc.perform(get("/reportquery/checkSyncReports"))

        and:
        archReportQueryController.findAllSyncCheckReports()

        then:
        def e = thrown(FeignException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle other exception thrown from findAllSyncCheckReports() and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from other library."
        def handledMessage = "External server error. "

        and:
        archFacade.allCheckReports() >> { List<CheckReportQueryView> reportQueryViewList ->
            throw new RuntimeException(messageDetails)
        }
        when:
        def response = mvc.perform(get("/reportquery/checkSyncReports"))

        and:
        archReportQueryController.findAllSyncCheckReports()

        then:
        def e = thrown(RuntimeException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }


    def " should return checkReportQueryView and HTTP status code OK"() {
    }

}


