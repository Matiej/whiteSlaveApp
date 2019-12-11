package com.whiteslave.whiteslaveApp.searchReport;

import com.whiteslave.whiteslaveApp.searchReport.domain.SearchReportService;
import com.whiteslave.whiteslaveApp.searchReport.domain.dto.SearchReportDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search/")
@RequiredArgsConstructor
public class SearchReportController {

    private final SearchReportService searchReportService;

    @GetMapping("/bankAccount/date")
    @ApiOperation(value = "Search company by bank account no and date. ", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    //todo walidacje na parametrach, dodrobic validtary
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "bankAccount", value = "enter bank account no", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review", dataTypeClass = String.class, paramType = "query", example = "2019-12-01")

    })
    ResponseEntity<Object> searchByBankAccountAndDate(@RequestParam("bankAccount") String bankAccount,
                                                      @RequestParam("date") String date) {
        SearchReportDto searchReportDto = searchReportService.searchByBankAccountAndDate(bankAccount, date);
        return ResponseEntity.ok(searchReportDto);
    }
}
