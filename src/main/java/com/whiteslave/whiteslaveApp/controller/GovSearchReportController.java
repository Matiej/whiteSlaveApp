package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.reportSync.ReportDtoFacade;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.dto.SearchReportDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
@Api(description = "Search for company reports/information in gov white list.")
@CrossOrigin(origins = "http://localhost:4200")
public class GovSearchReportController {

    private final ReportDtoFacade reportDtoFacade;

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
        return ResponseEntity.ok(reportDtoFacade.searchAndSynchronizeByBankAccountAndDate(bankAccount,date));
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
        return ResponseEntity.ok(reportDtoFacade.searchAndSynchronizeByBankAccountsAndDate(bankAccounts,date));
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
        return ResponseEntity.ok().body(reportDtoFacade.searchAndSynchronizeByNipAndDate(nip,date));
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
        return ResponseEntity.ok(reportDtoFacade.searchAndSynchronizeByNipsAndDate(nips,date));
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
        return ResponseEntity.ok(reportDtoFacade.searchAndSynchronizeByRegonAndDate(regon,date));
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
            @ApiImplicitParam(required = true, name = "bankAccounts", value = "Up to 30 regon numbers separated by a comma. " +
                    "Without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> searchByRegonsAndDate(@RequestParam String regons, @RequestParam String date) {
        return ResponseEntity.ok(reportDtoFacade.searchAndSynchronizeByRegonsAndDate(regons,date));
    }

}
