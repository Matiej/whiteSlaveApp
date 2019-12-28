package com.whiteslave.whiteslaveApp.validator;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Map;

public abstract class GovNumbersSharedValidator {

    private final static String DIGIT_REGEX = "[0-9]+";
    private final static String MULTI_PARAM_SEPARATOR = ",";
    private ConstraintValidatorContext constraintValidatorContext;

    protected boolean paramsValidateDistributor(String params, ConstraintValidatorContext constraintValidatorContext,
                                                Map<String, String> messages, int... length) {
        this.constraintValidatorContext = constraintValidatorContext;
        boolean isParamValid = false;
        if (params.contains(MULTI_PARAM_SEPARATOR)) {
            for (String param : params.split(MULTI_PARAM_SEPARATOR)) {
                if (!isParamValid(param, messages, length)) {
                    isParamValid = false;
                    break;
                }
                isParamValid = true;
            }
        } else {
            isParamValid = isParamValid(params, messages, length);
        }
        return isParamValid;
    }

    private boolean isParamValid(String param, Map<String, String> messages, int[] length) {
        if (!lengthValidation(length, param)) {
            prepareMessage(messages.get("LENGTH_MESSAGE") + param);
            return false;
        }
        if (!digitValidation(param)) {
            prepareMessage(messages.get("DIGIT_MESSAGE") + param);
            return false;
        }
        if (!checksumValidation(param)) {
            prepareMessage(messages.get("CHECK_SUM_MESSAGE") + param);
            return false;
        }
        return true;
    }

    protected boolean lengthValidation(int[] requiredLength, String param) {
        return Arrays.stream(requiredLength).anyMatch(length -> length == param.length());
    }

    protected boolean digitValidation(String param) {
        return param.matches(DIGIT_REGEX);
    }

    abstract boolean checksumValidation(String param);

    protected void prepareMessage(String message) {
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
