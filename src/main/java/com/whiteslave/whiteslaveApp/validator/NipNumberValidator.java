package com.whiteslave.whiteslaveApp.validator;

import com.google.common.collect.ImmutableMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class NipNumberValidator extends GovNumbersSharedValidator implements ConstraintValidator<NipNumber, String> {

    private final static String LENGTH_MESSAGE = "NIP number has incorrect length. Required 10 characters => ";
    private final static String DIGIT_MESSAGE = "NIP number contains illegal characters. Only digits required=> ";
    private final static String CHECK_SUM_MESSAGE = "NIP number checksum is not valid => ";
    private final static int[] WEIGHT = {6, 5, 7, 2, 3, 4, 5, 6, 7};
    private final static int NIP_LENGTH = 10;

    @Override
    public void initialize(NipNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nips, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Map<String, String> messageMap = ImmutableMap
                .of("LENGTH_MESSAGE", LENGTH_MESSAGE,
                        "DIGIT_MESSAGE", DIGIT_MESSAGE,
                        "CHECK_SUM_MESSAGE", CHECK_SUM_MESSAGE);
        return paramsValidateDistributor(nips, constraintValidatorContext, messageMap, NIP_LENGTH);
    }

    @Override
    boolean checksumValidation(String param) {
        try {
            int sum = 0;
            for (int i = 0; i < WEIGHT.length; ++i) {
                sum += Integer.parseInt(param.substring(i, i + 1)) * WEIGHT[i];
            }
            return (sum % 11) == Integer.parseInt(param.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
