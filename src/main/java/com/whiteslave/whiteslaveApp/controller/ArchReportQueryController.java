package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.reportSync.domain.enums.ReportType;
import com.whiteslave.whiteslaveApp.archReportQuery.ArchCheckReportQueryDto;
import com.whiteslave.whiteslaveApp.archReportQuery.ArchRepotQueryRepository;
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
@Api(description = "some text")
@CrossOrigin(origins = "http://localhost:4200")
class ArchReportQueryController {

    private final ArchRepotQueryRepository repository;

    @GetMapping("/checkSyncReports")
    @ApiOperation(value = "Check for quick company reports saved in data base. Include query and gov response ", response = ArchCheckReportQueryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any reports from data base."),
    })
    public ResponseEntity<Object> findAllSyncCheckReports() {
        List<ArchCheckReportQueryDto> byReportType = repository.findAllCheckReports(ReportType.CHECK);
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
        ArchCheckReportQueryDto checkReportById = repository.findCheckReportById(ReportType.CHECK, id);
        return ResponseEntity.ok().body(checkReportById);
    }
}
