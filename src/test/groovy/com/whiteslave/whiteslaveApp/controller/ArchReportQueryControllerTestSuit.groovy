package com.whiteslave.whiteslaveApp.controller

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.ArchReportQueryFacade
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchReportDetailsQueryView
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

//todo zrobic testy błędnych uri 400
class ArchReportQueryControllerTestSuit extends Specification {

    protected MockMvc mvc

    def archFacade = Mock(ArchReportQueryFacade)

    def archReportQueryController = new ArchReportQueryController(archFacade)

    @Shared
    List<CheckReportQueryView> reportQueryViewList;

    @Shared
    List<SearchPositiveReportQueryView> searchPositiveReportQueryViewList

    @Shared
    CheckReportQueryView reportQueryView;

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
        given:
        def givenObj = new Object() as CheckReportQueryView
        archFacade.findCheckReportTypeAndById(_ as Long) >>> [givenObj, null]

        when:
        ResponseEntity<CheckReportQueryView> respone = archReportQueryController.findSyncCheckReportById(1L) as ResponseEntity<CheckReportQueryView>

        then:
        respone.getBody() != null
        assert respone.getStatusCode() == HttpStatus.OK

        when:
        def reposnse2Null = archReportQueryController.findSyncCheckReportById(2L) as ResponseEntity<CheckReportQueryView>

        then:
        reposnse2Null.getBody() == null
        assert respone.getStatusCode() == HttpStatus.OK
    }

    def "should handle HibernateException thrown from findSyncCheckReportById and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Data Base server error. Can not get or save any report."

        and:
        archFacade.findCheckReportTypeAndById(_ as Long) >> { throw new HibernateException(messageDetails) }

        when:
        def responseHibernate = mvc.perform(get("/reportquery/checkSyncReport").param("id", "2"))

        and:
        archReportQueryController.findSyncCheckReportById(2L)

        then:
        def e = thrown(HibernateException)
        assert e.getMessage() == messageDetails

        and:
        responseHibernate.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseHibernate.andExpect(jsonPath('$.message').value(handledMessage))
        responseHibernate.andExpect(jsonPath('$.details').value(messageDetails))

    }

    def "should handle FeignException thrown from findSyncCheckReportById and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Gov resource server error. Can not send and receive any report"
        def feignStatus = 503

        and:
        archFacade.findCheckReportTypeAndById(_ as Long) >> { throw new FeignException(feignStatus, messageDetails) }

        when:
        def responseFeign = mvc.perform(get("/reportquery/checkSyncReport").param("id", "1"))

        and:
        archReportQueryController.findSyncCheckReportById(1L)

        then:
        def e = thrown(FeignException)
        assert e.getLocalizedMessage() == messageDetails

        and:
        responseFeign.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseFeign.andExpect(jsonPath('$.message').value(handledMessage))
        responseFeign.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle other exception thrown from findSyncCheckReportById and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from other library."
        def handledMessage = "External server error. "

        and:
        archFacade.findCheckReportTypeAndById(_ as Long) >> { throw new RuntimeException(messageDetails) }

        when:
        def responseOther = mvc.perform(get("/reportquery/checkSyncReport").param("id", "2"))

        and:
        archReportQueryController.findSyncCheckReportById(2L)

        then:
        def e = thrown(RuntimeException)
        assert e.getMessage() == messageDetails

        and:
        responseOther.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseOther.andExpect(jsonPath('$.message').value(handledMessage))
        responseOther.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should return list of findAllSyncSearchReports and HTTP status code OK"() {
        given:
        archFacade.allSearchReports() >> searchPositiveReportQueryViewList

        when:
        ResponseEntity<List<SearchPositiveReportQueryView>> result = archReportQueryController.findAllSyncSearchReports()

        and:
        def response = mvc.perform(get("/reportquery/searchSyncReports"))

        then:
        result != null
        assert result.statusCode == HttpStatus.OK

        and:
        response.andExpect(status().isOk())

    }

    def "should handle HibernateException thrown from findAllSyncSearchReports and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Data Base server error. Can not get or save any report."

        and:
        archFacade.allSearchReports() >> { List<SearchPositiveReportQueryView> searchPositiveReportQueryViewList ->
            throw new HibernateException(messageDetails)
        }

        when:
        def response = mvc.perform(get("/reportquery/searchSyncReports"))


        and:
        archReportQueryController.findAllSyncSearchReports()

        then:
        def e = thrown(HibernateException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle FeignException thrown from findAllSyncSearchReports and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from Feign library."
        def handledMessage = "Gov resource server error. Can not send and receive any report"
        def feignStatus = 503

        and:
        archFacade.allSearchReports() >> { List<SearchPositiveReportQueryView> searchPositiveReportQueryViewList ->
            throw new FeignException(feignStatus, messageDetails)
        }
        when:
        def response = mvc.perform(get("/reportquery/searchSyncReports"))

        and:
        archReportQueryController.findAllSyncSearchReports()

        then:
        def e = thrown(FeignException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle other exception thrown from findAllSyncSearchReports and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from other library."
        def handledMessage = "External server error. "

        and:
        archFacade.allSearchReports() >> { List<SearchPositiveReportQueryView> searchPositiveReportQueryViewList ->
            throw new RuntimeException(messageDetails)
        }
        when:
        def response = mvc.perform(get("/reportquery/searchSyncReports"))

        and:
        archReportQueryController.findAllSyncSearchReports()

        then:
        def e = thrown(RuntimeException)
        assert e.getMessage() == messageDetails

        and:
        response.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        response.andExpect(jsonPath('$.message').value(handledMessage))
        response.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def " should return findSyncSearchReportDetailsById and HTTP status code OK"() {
        given:
        def givenObj = new Object() as SearchReportDetailsQueryView
        archFacade.findSearchReportDetailsById(_ as Long) >>> [givenObj, null]

        when:
        ResponseEntity<SearchReportDetailsQueryView> respone = archReportQueryController.findSyncSearchReportDetailsById(1L) as ResponseEntity<SearchReportDetailsQueryView>

        then:
        respone.getBody() != null
        assert respone.getStatusCode() == HttpStatus.OK

        when:
        def reposnse2Null = archReportQueryController.findSyncSearchReportDetailsById(2L) as ResponseEntity<SearchReportDetailsQueryView>

        then:
        reposnse2Null.getBody() == null
        assert respone.getStatusCode() == HttpStatus.OK
    }

    def "should handle HibernateException thrown from findSyncSearchReportDetailsById and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Data Base server error. Can not get or save any report."

        and:
        archFacade.findSearchReportDetailsById(_ as Long) >> { throw new HibernateException(messageDetails) }

        when:
        def responseHibernate = mvc.perform(get("/reportquery/searchSyncReport").param("id", "2"))

        and:
        archReportQueryController.findSyncSearchReportDetailsById(2L)

        then:
        def e = thrown(HibernateException)
        assert e.getMessage() == messageDetails

        and:
        responseHibernate.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseHibernate.andExpect(jsonPath('$.message').value(handledMessage))
        responseHibernate.andExpect(jsonPath('$.details').value(messageDetails))

    }

    def "should handle FeignException thrown from findSyncSearchReportDetailsById and HttpStatus SERVICE_UNAVAILABLE"() {
        given:
        def messageDetails = "Some exception messageDetails from library."
        def handledMessage = "Gov resource server error. Can not send and receive any report"
        def feignStatus = 503

        and:
        archFacade.findSearchReportDetailsById(_ as Long) >> { throw new FeignException(feignStatus, messageDetails) }

        when:
        def responseFeign = mvc.perform(get("/reportquery/searchSyncReport").param("id", "1"))

        and:
        archReportQueryController.findSyncSearchReportDetailsById(1L)

        then:
        def e = thrown(FeignException)
        assert e.getLocalizedMessage() == messageDetails

        and:
        responseFeign.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseFeign.andExpect(jsonPath('$.message').value(handledMessage))
        responseFeign.andExpect(jsonPath('$.details').value(messageDetails))
    }

    def "should handle other exception thrown from findSyncSearchReportDetailsById and HttpStatus SERVICE_UNAVAILABLE "() {
        given:
        def messageDetails = "Some exception messageDetails from other library."
        def handledMessage = "External server error. "

        and:
        archFacade.findSearchReportDetailsById(_ as Long) >> { throw new RuntimeException(messageDetails) }

        when:
        def responseOther = mvc.perform(get("/reportquery/searchSyncReport").param("id", "2"))

        and:
        archReportQueryController.findSyncSearchReportDetailsById(2L)

        then:
        def e = thrown(RuntimeException)
        assert e.getMessage() == messageDetails

        and:
        responseOther.andExpect(status().is(HttpStatus.SERVICE_UNAVAILABLE.value()))
        responseOther.andExpect(jsonPath('$.message').value(handledMessage))
        responseOther.andExpect(jsonPath('$.details').value(messageDetails))
    }


}


