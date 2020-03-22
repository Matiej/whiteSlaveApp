package com.whiteslave.whiteslaveApp;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
//        String s = LocalDate.now().toString();
//        System.out.println(s);
//        Date date = new Date();
//        System.out.println(date);

//        List<MfGovAuthorizedClerks> mfGovAuthorizedClerks = null;
//
//        List<AuthorizedClerksDto> authorizedClerksDtos = Optional.ofNullable(mfGovAuthorizedClerks)
//                .map(l -> l.stream()
//                        .map(mfa -> new AuthorizedClerksDto(mfa.getCompanyName(), mfa.getFirstName(), mfa.getLastName(), mfa.getNip(), mfa.getPesel()))
//                        .collect(Collectors.toList()))
//                .orElse(new ArrayList<>());
//
//        System.out.println(authorizedClerksDtos.size());
//
//        List<MfGovSubject> mfGovSubjects = null;
//        List<SubjectDto> sou = Optional.ofNullable(mfGovSubjects)
//                .map(l -> l.stream()
//                        .map(mfs -> SubjectDto.builder()
//                                .name(mfs.getName())
//                                .nip(mfs.getNip())
//                                .statusVat(mfs.getStatusVat())
//                                .regon(mfs.getRegon())
//                                .pesel(mfs.getPesel())
//                                .krs(mfs.getKrs())
//                                .residenceAddress(mfs.getResidenceAddress())
//                                .workingAddress(mfs.getWorkingAddress())
////                                .representativesDtoList(convert2Representatives(mfs.getMfGovRepresentativesList()))
////                                .authorizedClerksDtoList(convert2AuthorizedClerksDto(mfs.getMfGovAuthorizedClerksList()))
////                                .partnersDtoList(convert2PartnersDto(mfs.getMfGovPartnersList()))
//                                .registrationLegalDate(mfs.getRegistrationLegalDate())
//                                .registrationDenialBasis(mfs.getRegistrationDenialBasis())
//                                .registrationDenialDate(mfs.getRegistrationDenialDate())
//                                .restorationBasis(mfs.getRestorationBasis())
//                                .restorationDate(mfs.getRestorationDate())
//                                .removalBasis(mfs.getRemovalBasis())
//                                .removalDate(mfs.getRemovalDate())
//                                .accountNumbersList(mfs.getAccountNumbersList())
//                                .hasVirtualAccounts(mfs.getHasVirtualAccounts())
//                                .build())
//                        .collect(Collectors.toList()))
//                .orElse(new ArrayList<>());
//
//        System.out.println(sou.size());
//        String max =
//                "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942," +
//                        "55105000995102004501807942";
//        System.out.println(max);
//        System.out.println("\n" +max.length());
//
//        IBANValidator defaultIbanValidator = IBANValidator.DEFAULT_IBAN_VALIDATOR;
//        boolean valid = defaultIbanValidator.isValid("PL31114020170000400211870468");
//        System.out.println("czy dobry-> " + valid);
//        String vali = "[0-9]+";

//        System.out.println("$$$$$$$$$$$$$$");
////        System.out.println("31114020170000400211870468".matches(vali));
//        List<String> aa = new ArrayList<>();
//        aa.add("A");
//        aa.add("B");
//        aa.add("C");
//        aa.add("D");
//
//        for (String s : aa) {
//            if (s.equals("B")) {
//                System.out.println("IFer " + s);
//                break;
//            }
//            System.out.println(s);

//        final String ACCOUNT_SEPARATOR = ",";
//        String s = "55105000995102004501807942,, 21,,31114020170000400211870468,,,,,";
//
//        String bankAccount = s.strip().replaceAll("-", "");
//        if (bankAccount.contains(ACCOUNT_SEPARATOR)) {
//            for (String account : s.split(ACCOUNT_SEPARATOR)) {
//                String trimmedAccount = account.trim();
//                if(!trimmedAccount.isEmpty() || !trimmedAccount.isBlank()) {
//                    System.out.println("account-> " + account + " l: " + account.length());
//                }
//            }
//        }

        int[] regons = new int[]{9, 14};
        String shortRegon = "123456785";
        String longRegon = "02807465847456";
        String regon = "65465";

        int sumka = 1 * 8 + 2 * 9 + 3 * 2 + 4 * 3 + 5 * 4 + 6 * 5 + 7 * 6 + 8 * 7;
        System.out.println(sumka);
        String somedate = "2019-12-25";
        boolean isDateV = dateV(somedate);
        System.out.println(isDateV);
//        boolean b = checksumValidation(shortRegon);
//        System.out.println(b);
//        System.out.println(checkSum14(longRegon));

//        System.out.println(lengthValidation(regons,shortRegon));
//        System.out.println(lengthValidation(regons,longRegon));
//        System.out.println(lengthValidation(regons,regon));
    }

    static boolean dateV(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate testDate = LocalDate.parse(date);
        boolean after = testDate.isAfter(LocalDate.now());

        return after;
    }

    static boolean lengthValidation(int[] requiredLength, String param) {
        return Arrays.stream(requiredLength).anyMatch(length -> length == param.length());
    }

    static boolean checksumValidation(String param) {
        int[] WEIGHT_9_DIGIT = new int[]{8, 9, 2, 3, 4, 5, 6, 7};
        AtomicInteger sum = new AtomicInteger();
        IntStream.range(0, WEIGHT_9_DIGIT.length).forEach(i -> {
            int i1 = WEIGHT_9_DIGIT[i];
            int c = Integer.parseInt(String.valueOf(param.charAt(i)));
            sum.addAndGet(i1 * c);
        });
        return Optional.of(sum.get())
                .map(s -> s % 11)
                .map(s -> {
                    if (s == 10) {
                        s = 0;
                    }
                    if (s == Integer.parseInt(param.substring(param.length() - 1))) {
                        return true;
                    }
                    return false;
                }).orElse(false);
    }

    static boolean checkSum14(String regon14) {
        int[] WEIGHT_14_DIGIT = new int[]{2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8};
        AtomicInteger sum = new AtomicInteger();
        IntStream.range(0, WEIGHT_14_DIGIT.length).forEach(i -> {
            sum.addAndGet(WEIGHT_14_DIGIT[i] *
                    Integer.parseInt(String.valueOf(regon14.charAt(i))));
        });
        return Optional.of(sum.get())
                .map(s -> s % 11)
                .map(s -> {
                    if (s == 10) {
                        s = 0;
                    }
                    return s == Integer.parseInt(regon14.substring(regon14.length() - 1));
                })
                .orElse(false);
    }

//    private static boolean checkSum9(String regon9) {
//        int[] WEIGHT_9_DIGIT = new int[]{8, 9, 2, 3, 4, 5, 6, 7};
//        char c = '0';
//        int x = Integer.parseInt(String.valueOf(c));
//        System.out.println(x);
//        IntStream.range(0, regon9.length()).forEach(i -> {
//            System.out.println((int) regon9.charAt(i));
//        });
//        regon9.codePoints().forEach(t -> {
//            System.out.println("gowno: " + String.valueOf(t));
//        });
//
//        System.out.println("######");
//        char[] t1 = regon9.toCharArray();
//        System.out.println("lenght: " + t1.length);
//        Object[] objects = Stream.of(t1).map(String::valueOf).map(Integer::parseInt).toArray();
//        System.out.println("ARRASY AS:");
//        Arrays.stream(objects).forEach(System.out::print);
//        AtomicInteger sum = new AtomicInteger();
//        int sum1 = 0;
////        Arrays.stream(WEIGHT_9_DIGIT).forEach(r-> sum.getAndAdd(r * (int) regonArray[r]));
//        IntStream.range(0, WEIGHT_9_DIGIT.length).forEach(r -> {
//
//            sum.getAndAdd(WEIGHT_9_DIGIT[r] * regon9.toCharArray()[r]);
//        });
//
//
//        return true;


//        sum %= 11;
//        if (sum == 10) {
//            sum = 0;
//        }
//        if (sum == REGON[8]) {
//            return true;
//        }
//        else {
}

