package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.reportSync.ReportFacade;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.whiteslave.whiteslaveApp.controller.headerHandler.HttpHeaderFactory.getSuccessfulHeaders;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Api(description = "Search for company reports/information in gov white list.")
@CrossOrigin(origins = "${cross.origin.webui}")
class GovSearchReportController {

    private final ReportFacade reportFacade;

    @GetMapping("/bankAccount/date")
    @ApiOperation(value = "Search company by bank account number and date. ", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "bankAccount", value = "Bank account number without dashes, spaces and country prefix.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByBankAccountAndDate(@RequestParam("bankAccount") String bankAccount,
                                                      @RequestParam("date") String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByBankAccountAndDate(bankAccount, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

    @GetMapping("/bankAccounts/date")
    @ApiOperation(value = "Search company by max 30 bank account numbers(only first 30 will be check)and date.", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "bankAccounts", value = "Up to 30 bank accounts separated by a comma. " +
                    "Without dashes, spaces and country prefix.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByBankAccountsAndDate(@RequestParam("bankAccounts") String bankAccounts,
                                                       @RequestParam("date") String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByBankAccountsAndDate(bankAccounts, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

    @GetMapping("/nip/date")
    @ApiOperation(value = "Search company by nip number and date. ", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "nip", value = "Nip number without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByNipAndDate(@RequestParam("nip") String nip,
                                              @RequestParam("date") String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByNipAndDate(nip, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

    @GetMapping("/nips/date")
    @ApiOperation(value = "Search company by max 30 nip numbers(only first 30 will be check) and date. ", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "nips", value = "Up to 30 bank accounts separated by a comma. " +
                    "Without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByNipsAndDate(@RequestParam("nips") String nips, @RequestParam("date") String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByNipsAndDate(nips, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

    //todo nie wiem czy tutaj to zostawić. Może do innej klasy jakieś przenieść?? hm
    @GetMapping("/regon/date")
    @ApiOperation(value = "Search company by regon number and date. ", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "regon", value = "Regon number without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByRegonAndDate(@RequestParam("regon") String regon, @RequestParam("date") String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByRegonAndDate(regon, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

    @GetMapping("/regons/date")
    @ApiOperation(value = "Search company by max 30 regon numbers(only first 30 will be check)and date.", response = SearchReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Data Base server error. Can't get or save any report."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "regons", value = "Up to 30 regon numbers separated by a comma. " +
                    "Without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByRegonsAndDate(@RequestParam String regons, @RequestParam String date) {
        SearchReportDto searchReportDto = reportFacade.searchAndSynchronizeByRegonsAndDate(regons, date);
        return ResponseEntity.ok()
                .headers(getSuccessfulHeaders())
                .body(searchReportDto);
    }

}
