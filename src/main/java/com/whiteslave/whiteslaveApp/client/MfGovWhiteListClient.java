package com.whiteslave.whiteslaveApp.client;

import com.whiteslave.whiteslaveApp.client.dto.MfGovCheckResource;
import com.whiteslave.whiteslaveApp.client.dto.MfGovSearchResource;
import com.whiteslave.whiteslaveApp.client.dto.MfGovSearchResources;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface MfGovWhiteListClient {

    @GetMapping(value = "search/bank-account/{bank-account}", produces = "application/json")
    MfGovSearchResources searchByBankAccountAndDate(@PathVariable("bank-account") String bankAccount, @RequestParam("date") String date);

    @GetMapping(value = "search/bank-accounts/{bank-accounts}", produces = "application/json")
    MfGovSearchResources searchByBankAccountsAndDate(@PathVariable("bank-accounts") String bankAccounts, @RequestParam("date") String date);

    @GetMapping(value = "check/nip/{nip}/bank-account/{bank-account}", produces = "application/json")
    MfGovCheckResource checkByNipAndBankAccoutAndDate(
            @PathVariable("nip") String nip,
            @PathVariable("bank-account") String bankAccount,
            @RequestParam("date") String date);

    @GetMapping(value = "check/regon/{regon}/bank-account/{bank-account}", produces = "application/json")
    MfGovCheckResource checkByRegonAndBankAccoutnAndDate(
            @PathVariable("regon") String regon,
            @PathVariable("bank-account") String bankAccount,
            @RequestParam("date") String date);

    @GetMapping(value = "search/nip/{nip}", produces = "application/json")
    MfGovSearchResource searchByNipAndDate(@PathVariable("nip") String nip, @RequestParam String date);

    @GetMapping(value = "search/nips/{nips}", produces = "application/json")
    MfGovSearchResources searchByNipsAndDate(@PathVariable("nips") String nips, @RequestParam String date);

    @GetMapping(value = "search/regon/{regon}", produces = "application/json")
    MfGovSearchResource searchByRegonAndDate(@PathVariable("regon") String regon, @RequestParam String date);

    @GetMapping(value = "search/regons/{regons}", produces = "application/json")
    MfGovSearchResources searchByRegonsAndDate(@PathVariable("regons") String regons, @RequestParam String date);

}

