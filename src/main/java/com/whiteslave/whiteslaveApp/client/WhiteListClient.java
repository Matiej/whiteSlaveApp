package com.whiteslave.whiteslaveApp.client;

import com.whiteslave.whiteslaveApp.client.dto.CheckResource;
import com.whiteslave.whiteslaveApp.client.dto.SearchResource;
import com.whiteslave.whiteslaveApp.client.dto.SearchResources;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface WhiteListClient {

    @GetMapping(value = "search/bank-account/{bank-account}", produces = "application/json")
    SearchResources searchByBankAccountAndDate(@PathVariable("bank-account") String bankAccount, @RequestParam("date") String date);

    @GetMapping(value = "search/bank-accounts/{bank-accounts}", produces = "application/json")
    SearchResources searchByBankAccountsAndDate(@PathVariable("bank-accounts") String bankAccounts, @RequestParam("date") String date);

    @GetMapping(value = "check/nip/{nip}/bank-account/{bank-account}", produces = "application/json")
    CheckResource checkByNipAndBankAccoutAndDate(
            @PathVariable("nip") String nip,
            @PathVariable("bank-account") String bankAccount,
            @RequestParam("date") String date);

    @GetMapping(value = "check/regon/{regon}/bank-account/{bank-account}", produces = "application/json")
    CheckResource checkByRegonAndBankAccoutnAndDate(
            @PathVariable("regon") String regon,
            @PathVariable("bank-account") String bankAccount,
            @RequestParam("date") String date);

    @GetMapping(value = "search/nip/{nip}", produces = "application/json")
    SearchResource searchByNipAndDate(@PathVariable("nip") String nip, @RequestParam String date);

    @GetMapping(value = "search/nips/{nips}", produces = "application/json")
    SearchResources searchByNipsAndDate(@PathVariable("nips") String nips, @RequestParam String date);

    @GetMapping(value = "search/regon/{regon}", produces = "application/json")
    SearchResource searchByRegonAndDate(@PathVariable("regon") String regon, @RequestParam String date);

    @GetMapping(value = "search/regons/{regons}", produces = "application/json")
    SearchResources searchByRegonsAndDate(@PathVariable("regons") String regons, @RequestParam String date);

}

