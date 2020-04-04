package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.archiveReport.archReportQuery.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reportquery")
@RequiredArgsConstructor
@Api(description = "Queries to the archive of reports with information from the government's white list.")
@CrossOrigin(origins = "http://localhost:4200")
class ArchReportQueryController {

    private final ArchRepotQueryRepository repository;
    private final SearchReportSubjectQueryRepository subjectQueryRepository;

    @GetMapping("/checkSyncReports")
    @ApiOperation(value = "Check for quick company reports saved in data base. Include query and gov response ", response = ArchCheckReportQueryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncCheckReports() {
        List<ArchCheckReportQueryDto> byReportType = repository.findAllCheckReports();
        return ResponseEntity.ok().body(byReportType);
    }

    @GetMapping("/checkSyncReport")
    @ApiOperation(value = "Check for quick company report by ID param saved in data base. Include query and gov response ", response = ArchCheckReportQueryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "id", value = "Check report ID",
                    dataTypeClass = String.class, paramType = "query")
    })
    public ResponseEntity<Object> findSyncCheckReportById(@RequestParam("id") Long id) {
        ArchCheckReportQueryDto checkReportById = repository.findCheckReportById(id);
        return ResponseEntity.ok().body(checkReportById);
    }

    @GetMapping("/searchSyncReports")
    @ApiOperation(value = "Check for detail company reports saved in data base. Include query and gov response ", response = SearchReportSubjectQueryView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncSearchReportsV() {
        List<SearchReportSubjectQueryView> searchReports = subjectQueryRepository.findBy();
        return ResponseEntity.ok().body(searchReports);
    }

}
