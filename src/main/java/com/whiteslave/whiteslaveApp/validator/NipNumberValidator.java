package com.whiteslave.whiteslaveApp.validator;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections.map.UnmodifiableMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

public class NipNumberValidator extends ParamsSharedValidator implements ConstraintValidator<NipNumber, String> {

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
//        boolean isNip = false;
//        if (nips.contains(NIP_SEPARATOR)) {
//            for (String nipNumber : nips.split(NIP_SEPARATOR)) {
//                if (!isNipNumberValid(nipNumber)) {
//                    isNip = false;
//                    break;
//                }
//                isNip = true;
//            }
//        } else {
//            isNip = isNipNumberValid(nips);
//        }
//        return isNip;
    }
//
//    private boolean isNipNumberValid(String nip) {
//        if (!lengthValidation(NIP_LENGTH, nip, constraintValidatorContext)) {
//            prepareMessage(LENGTH_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        if (!digitValidation(nip, constraintValidatorContext)) {
//            prepareMessage(DIGIT_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        if (!checksumValidation(nip)) {
//            prepareMessage(CHECK_SUM_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        return true;
//
//        if (lengthValidation(nip, constraintValidatorContext)) {
//            if (digitValidation(nip, constraintValidatorContext)) {
//                if (isNipNumbertValid(nip, constraintValidatorContext)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    private boolean lengthValidation(String nip, ConstraintValidatorContext constraintValidatorContext) {
//        if (nip.length() > 0 & nip.length() <= 26) {
//            return true;
//        } else {
//            constraintValidatorContext
//                    .buildConstraintViolationWithTemplate("NIP number has incorrect length. Required 26 characters => " + nip)
//                    .addConstraintViolation();
//            return false;
//        }
//    }
//
//    private boolean digitValidation(String nip, ConstraintValidatorContext constraintValidatorContext) {
//        if (nip.matches(DIGIT_REGEX)) {
//            return true;
//        } else {
//            constraintValidatorContext
//                    .buildConstraintViolationWithTemplate("NIP number contains illegal characters. Only digits required=> " + nip)
//                    .addConstraintViolation();
//            return false;
//        }
//    }

//    private boolean isNipValid(String nip, ConstraintValidatorContext constraintValidatorContext) {
////        if (nip.length() != 10) {
////            return false;
////        }
//        try {
//            int sum = 0;
//            for (int i = 0; i < WEIGHT.length; ++i) {
//                sum += Integer.parseInt(nip.substring(i, i + 1)) * WEIGHT[i];
//            }
//            boolean isNipGit = (sum % 11) == Integer.parseInt(nip.substring(9, 10));
//            if (isNipGit) {
//                return isNipGit;
//            } else {
//                constraintValidatorContext
//                        .buildConstraintViolationWithTemplate("NIP number is not valid => " + nip)
//                        .addConstraintViolation();
//                return isNipGit;
//            }
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }

//    @Override
//    boolean isParamValid(String nip) {
//        if (!lengthValidation(NIP_LENGTH, nip, constraintValidatorContext)) {
//            prepareMessage(LENGTH_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        if (!digitValidation(nip, constraintValidatorContext)) {
//            prepareMessage(DIGIT_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        if (!checksumValidation(nip)) {
//            prepareMessage(CHECK_SUM_MESSAGE + nip, constraintValidatorContext);
//            return false;
//        }
//        return true;
//    }

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
