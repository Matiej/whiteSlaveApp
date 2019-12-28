package com.whiteslave.whiteslaveApp.validator;

import com.google.common.collect.ImmutableMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class RegonNumberValidator extends GovNumbersSharedValidator implements ConstraintValidator<RegonNumber, String> {
    private final static int[] WEIGHT_9_DIGIT = new int[]{8, 9, 2, 3, 4, 5, 6, 7};
    private final static int[] WEIGHT_14_DIGIT = new int[]{2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8};
    private final static String LENGTH_MESSAGE = "Regon number has incorrect length. Required 9 or 14 characters => ";
    private final static String DIGIT_MESSAGE = "Regon number contains illegal characters. Only digits required=> ";
    private final static String CHECK_SUM_MESSAGE = "Regon number checksum is not valid. => ";
    private final static int SHORT_REGON_LENGTH = 9;
    private final static int LONG_REGON_LENGTH = 14;

    @Override
    public void initialize(RegonNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String regons, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Map<String, String> messageMap = ImmutableMap
                .of("LENGTH_MESSAGE", LENGTH_MESSAGE,
                        "DIGIT_MESSAGE", DIGIT_MESSAGE,
                        "CHECK_SUM_MESSAGE", CHECK_SUM_MESSAGE);
        return paramsValidateDistributor(regons, constraintValidatorContext, messageMap, SHORT_REGON_LENGTH, LONG_REGON_LENGTH);
    }

    @Override
    boolean checksumValidation(String param) {
        if (param.length() == SHORT_REGON_LENGTH) {
            return checkSum9_14(param, WEIGHT_9_DIGIT);
        }
        return checkSum9_14(param,WEIGHT_14_DIGIT);
    }

    private boolean checkSum9_14(String regon9, int[] weight) {
        AtomicInteger sum = new AtomicInteger();
        IntStream.range(0, weight.length).forEach(i ->
                sum.addAndGet(weight[i] *
                        Integer.parseInt(String.valueOf(regon9.charAt(i)))));
        return Optional.of(sum.get())
                .map(s -> s % 11)
                .map(s -> {
                    if (s == 10) {
                        s = 0;
                    }
                    return s == Integer.parseInt(regon9.substring(regon9.length() - 1));
                }).orElse(false);

    }

//    private boolean checkSum9(String regon9) {
//        AtomicInteger sum = new AtomicInteger();
//        IntStream.range(0, WEIGHT_9_DIGIT.length).forEach(i ->
//                sum.addAndGet(WEIGHT_9_DIGIT[i] *
//                        Integer.parseInt(String.valueOf(regon9.charAt(i)))));
//        return Optional.of(sum.get())
//                .map(s -> s % 11)
//                .map(s -> {
//                    if (s == 10) {
//                        s = 0;
//                    }
//                    return s==Integer.parseInt(regon9.substring(regon9.length() - 1));
//                }).orElse(false);
//    }
//
//    private boolean checkSum14(String regon14) {
//        AtomicInteger sum = new AtomicInteger();
//        IntStream.range(0, WEIGHT_14_DIGIT.length).forEach(i ->
//                sum.addAndGet(WEIGHT_14_DIGIT[i] *
//                        Integer.parseInt(String.valueOf(regon14.charAt(i)))));
//        return Optional.of(sum.get())
//                .map(s -> s % 11)
//                .map(s -> {
//                    if(s == 10) {
//                        s = 0;
//                    }
//                    return s == Integer.parseInt(regon14.substring(regon14.length() - 1));
//                })
//                .orElse(false);
//    }
}

