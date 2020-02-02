package com.whiteslave.whiteslaveApp;

import com.whiteslave.whiteslaveApp.govRequestReport.client.MfGovWhiteListClient;
import com.whiteslave.whiteslaveApp.govRequestReport.searchReport.domain.SearchReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner  implements CommandLineRunner {

    @Autowired
    private MfGovWhiteListClient mfGovWhiteListClient;

    @Autowired
    private SearchReportService searchReportService;

    @Override
    public void run(String... args) throws Exception {

//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^ STARTUJE SEARCH BY BANK ACCOUNT");
//        SearchReportDto searchReportDto = searchReportService.searchByBankAccountAndDate("84941126311377623540801172", "2019-12-05");
//        System.out.println(searchReportDto);
//        MfGovSearchResources searchByBankAccountAndDate = mfGovWhiteListClient.searchByBankAccountAndDate("84941126311377623540801172", "2019-12-05");
//        System.out.println(searchByBankAccountAndDate);
//
//        System.out.println("######################  STARTUJE SEARCH BY  BANK ACCOUNTS");
//        MfGovSearchResources mfGovSearchResources =
//                mfGovWhiteListClient.searchByBankAccountsAndDate("84941126311377623540801172,66085143216663255947280006,29756121919509167372993577,88611108446628352845002315", LocalDate.now().toString());
//        System.out.println(mfGovSearchResources);
//
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  STARTUJE CHECK by NIPS and BANK-ACCOUNT");
//        MfGovCheckResource mfGovCheckResource = mfGovWhiteListClient.checkByRegonAndBankAccoutnAndDate("93992478603234", "66085143216663255947280006", LocalDate.now().toString());
//        System.out.println(mfGovCheckResource);
//
//            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&  STARTUJE CHECK BY REGONS AND BANK-ACCOUNT");
//        MfGovCheckResource mfGovCheckResource1 = mfGovWhiteListClient.checkByNipAndBankAccoutAndDate("3245174504", "66085143216663255947280006", LocalDate.now().toString());
//        System.out.println(mfGovCheckResource1);
//
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  STARTUJE SERACH by bank account and date");
//        MfGovSearchResource wr = mfGovWhiteListClient.searchByNipAndDate("3245174504", LocalDate.now().toString());
//        System.out.println(wr);
//
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ STARTUJE SEARCH BY NIPS AND DATE");
//        MfGovSearchResources searchByNipsAndBankAccount = mfGovWhiteListClient.searchByNipsAndDate("3245174504,8661447759", "2019-12-01");
//        System.out.println(searchByNipsAndBankAccount);
//
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! STARTUJE SEARCH BY REGON AND DATE");
//        MfGovSearchResource searchByRegonAndDate = mfGovWhiteListClient.searchByRegonAndDate("285542125", LocalDate.now().toString());
//        System.out.println(searchByRegonAndDate);
//
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!! STARTUJE SEARCH BY REGONss AND DATE");
//        MfGovSearchResources searchByRegonsAndDate = mfGovWhiteListClient.searchByRegonsAndDate("93042163538079,23274699402511,803966511", LocalDate.now().toString());
//        System.out.println(searchByRegonsAndDate);
    }
}
