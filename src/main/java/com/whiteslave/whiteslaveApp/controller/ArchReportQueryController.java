package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.ArchReportQueryFacade;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.CheckReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchPositiveReportQueryView;
import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.view.SearchReportDetailsQueryView;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reportquery")
@RequiredArgsConstructor
@Api(description = "Queries to the archive of reports with information from the government's white list.")
@CrossOrigin(origins = "http://localhost:4200")
class ArchReportQueryController {

    private final ArchReportQueryFacade archReportQueryFacade;

    @GetMapping("/checkSyncReports")
    @ApiOperation(value = "Check for quick company reports saved in data base. Include query and gov response ", response = CheckReportQueryView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncCheckReports() {
        List<CheckReportQueryView> reportQueryViewList = archReportQueryFacade.allCheckReports();
        return ResponseEntity.ok().body(reportQueryViewList);
    }

    @GetMapping("/checkSyncReport")
    @ApiOperation(value = "Check for quick company report by ID param saved in data base. Include query and gov response ", response = CheckReportQueryView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No reports found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "id", value = "Check report ID",
                    dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<Object> findSyncCheckReportById(@RequestParam("id") Long id) {
        CheckReportQueryView checkReportById = archReportQueryFacade.findCheckReportTypeAndById(id);
        return ResponseEntity.ok().body(checkReportById);
    }

    @GetMapping("/searchSyncReports")
    @ApiOperation(value = "Check for detail company reports saved in data base. Include query and gov response ", response = SearchPositiveReportQueryView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No reports found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncSearchReports() {
        List<SearchPositiveReportQueryView> searchReports = archReportQueryFacade.allSearchReports();
        return ResponseEntity.ok().body(searchReports);
    }

    @GetMapping("/searchSyncReport")
    @ApiOperation(value = "Check for detail company reports saved in data base. Include query and gov response ",
            response = SearchReportDetailsQueryView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No reports found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "id", value = "Search report ID",
                    dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<Object> findSyncSearchReportDetailsById(@RequestParam("id") Long id) {
        SearchReportDetailsQueryView searchReportDetails = archReportQueryFacade.findSearchReportDetailsById(id);
        return ResponseEntity.ok().body(searchReportDetails);
    }


//    @GetMapping("/test")
//    @ApiOperation(value = "TO TEST", response = SearchPositiveReportQueryView.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Search successful"),
//            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
//            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
//            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(readOnly = true, name = "id", value = "Check report ID",
//                    dataTypeClass = String.class, paramType = "query")
//    })
//    public ResponseEntity<Object> findTest(@RequestParam("id") Long id) {
//        CheckReportQueryView oneBY = archReportQueryFacade.findCheckReportTypeAndById(id);
//        return ResponseEntity.ok().body(oneBY);
//    }

}
