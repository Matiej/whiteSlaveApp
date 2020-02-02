package com.whiteslave.whiteslaveApp.controller;

import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.CheckReportService;
import com.whiteslave.whiteslaveApp.govRequestReport.checkReport.domain.dto.CheckReportDto;
import com.whiteslave.whiteslaveApp.reportSync.ReportSyncService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
@Api(description = "Quick check for company information inf gov white list.")
@CrossOrigin(origins = "http://localhost:4200")
public class GovCheckReportController {

    private final CheckReportService checkReportService;
    private final ReportSyncService reportSyncService;

    @GetMapping("/nip&bankaccount/date")
    @ApiOperation(value = "Check for quick company information by nip, bank account number and date.", response = CheckReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "nip", value = "NIP number without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "bankAccount", value = "Bank account number without dashes, spaces and country prefix.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> checkByNipAndBankAccoutAndDate(@RequestParam("nip") String nip,
                                                          @RequestParam("bankAccount") String bankAccount,
                                                          @RequestParam("date") String date) {
        String preparedBankAccount = checkAndPrepareSignleValue(bankAccount);
        String preparedNip = checkAndPrepareSignleValue(nip);
        CheckReportDto checkReportDto = checkReportService.checkByNipAndBankAccoutAndDate(preparedNip, preparedBankAccount, date);
        reportSyncService.syncAndSaveCheckReport(checkReportDto, preparedNip, preparedBankAccount, date);
        return ResponseEntity.ok(checkReportDto);
    }

    @GetMapping("/regon&bankaccount/date")
    @ApiOperation(value = "Check for quick company information by regon, bank account number and date.", response = CheckReportDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search successful"),
            @ApiResponse(code = 400, message = "The request cannot be fulfilled because of wrong syntax"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any requests."),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(readOnly = true, name = "regon", value = "Regon number without dashes and spaces.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "bankAccount", value = "Bank account number without dashes, spaces and country prefix.",
                    dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(required = true, name = "date", value = "date of review",
                    dataTypeClass = String.class, paramType = "query", example = "2019-12-01")
    })
    ResponseEntity<Object> checkByRegonAndBankAccoutnAndDate(@RequestParam("regon") String regon,
                                                             @RequestParam("bankAccount") String bankAccount,
                                                             @RequestParam("date") String date) {
        String preparedBankAccount = checkAndPrepareSignleValue(bankAccount);
        String preparedReqgon = checkAndPrepareSignleValue(regon);
        CheckReportDto checkReportDto = checkReportService.checkByRegonAndBankAccoutnAndDate(preparedReqgon, preparedBankAccount, date);
        reportSyncService.syncAndSaveCheckReport(checkReportDto, preparedBankAccount, preparedReqgon,date);
        return ResponseEntity.ok(checkReportDto);
    }


    //todo nie wiem czy to powinno tu zostać. Raczej tmp
    private String checkAndPrepareSignleValue(String singleValue) {
        return singleValue.strip().replaceAll("-", "");
    }


}