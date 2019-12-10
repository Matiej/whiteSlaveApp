package com.whiteslave.whiteslaveApp;

import com.whiteslave.whiteslaveApp.client.WhiteListClient;
import com.whiteslave.whiteslaveApp.client.dto.CheckResource;
import com.whiteslave.whiteslaveApp.client.dto.SearchResource;
import com.whiteslave.whiteslaveApp.client.dto.SearchResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppRunner  implements CommandLineRunner {

    @Autowired
    private WhiteListClient whiteListClient;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^ STARTUJE SEARCH BY BANK ACCOUNT");
        SearchResources searchByBankAccountAndDate = whiteListClient.searchByBankAccountAndDate("84941126311377623540801172", "2019-12-05");
        System.out.println(searchByBankAccountAndDate);

        System.out.println("######################  STARTUJE SEARCH BY  BANK ACCOUNTS");
        SearchResources searchResources =
                whiteListClient.searchByBankAccountsAndDate("84941126311377623540801172,66085143216663255947280006,29756121919509167372993577,88611108446628352845002315", LocalDate.now().toString());
        System.out.println(searchResources);

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  STARTUJE CHECK by NIPS and BANK-ACCOUNT");
        CheckResource checkResource = whiteListClient.checkByRegonAndBankAccoutnAndDate("93992478603234", "66085143216663255947280006", LocalDate.now().toString());
        System.out.println(checkResource);

            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&  STARTUJE CHECK BY REGONS AND BANK-ACCOUNT");
        CheckResource checkResource1 = whiteListClient.checkByNipAndBankAccoutAndDate("3245174504", "66085143216663255947280006", LocalDate.now().toString());
        System.out.println(checkResource1);

            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  STARTUJE SERACH by bank account and date");
        SearchResource wr = whiteListClient.searchByNipAndDate("3245174504", LocalDate.now().toString());
        System.out.println(wr);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ STARTUJE SEARCH BY NIPS AND DATE");
        SearchResources searchByNipsAndBankAccount = whiteListClient.searchByNipsAndDate("3245174504,8661447759", "2019-12-01");
        System.out.println(searchByNipsAndBankAccount);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! STARTUJE SEARCH BY REGON AND DATE");
        SearchResource searchByRegonAndDate = whiteListClient.searchByRegonAndDate("285542125", LocalDate.now().toString());
        System.out.println(searchByRegonAndDate);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! STARTUJE SEARCH BY REGONss AND DATE");
        SearchResources searchByRegonsAndDate = whiteListClient.searchByRegonsAndDate("93042163538079,23274699402511,803966511", LocalDate.now().toString());
        System.out.println(searchByRegonsAndDate);
    }
}
