package com.whiteslave.whiteslaveApp.validator;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.validator.routines.IBANValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class BankAccountValidator extends ParamsSharedValidator implements ConstraintValidator<BankAccount, String> {

    private final static String LENGTH_MESSAGE = "Bank account number has incorrect length. Required 26 characters => ";
    private final static String DIGIT_MESSAGE = "Bank account contains illegal characters. Only digits required=> ";
    private final static String CHECK_SUM_MESSAGE = "Bank account number checksum is not valid. => ";
    private final static String IBAN_COUNTRY_CODE = "PL";
    private final static String ACCOUNT_SEPARATOR = ",";
    private final static int ACCOUNT_LENGTH = 26;

    @Override
    public void initialize(BankAccount constraintAnnotation) {
    }

    //    @SneakyThrows(BankAccountException.class)
    @Override
    public boolean isValid(String bankAccounts, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Map<String, String> messageMap = ImmutableMap
                .of("LENGTH_MESSAGE", LENGTH_MESSAGE,
                        "DIGIT_MESSAGE", DIGIT_MESSAGE,
                        "CHECK_SUM_MESSAGE", CHECK_SUM_MESSAGE);
        return paramsValidateDistributor(bankAccounts,constraintValidatorContext, messageMap, ACCOUNT_LENGTH);
//        boolean isBankAccunts = false;
//        if (bankAccounts.contains(ACCOUNT_SEPARATOR)) {
//            for (String account : bankAccounts.split(ACCOUNT_SEPARATOR)) {
//                if (!isBankAccountValid(account)) {
//                    isBankAccunts = false;
//                    break;
//                }
//                isBankAccunts = true;
//            }
//        } else {
//            isBankAccunts = isBankAccountValid(bankAccounts);
//        }
//        return isBankAccunts;
    }

//    private boolean isBankAccountValid(String bankAccounts) {
//        //todo to tez do biutifulizacji
//        if (!lengthValidation(ACCOUNT_LENGTH, bankAccounts, constraintValidatorContext)) {
//            prepareMessage(LENGTH_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        if (!digitValidation(bankAccounts, constraintValidatorContext)) {
//            prepareMessage(DIGIT_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        if (!checksumValidation(bankAccounts)) {
//            prepareMessage(CHECK_SUM_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        return true;
//        if (lengthValidation(ACCOUNT_LENGTH, bankAccounts, constraintValidatorContext)) {
//            if (digitValidation(bankAccounts, constraintValidatorContext)) {
//                if (checksumValidation(bankAccounts)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    private boolean lengthValidation(String bankAccount, ConstraintValidatorContext constraintValidatorContext) {
//        if (bankAccount.length() == 26) {
//            return true;
//        } else {
//            String message = "Bank account number has incorrect length. Required 26 characters => " + bankAccount;
//            prepareMessage(message, constraintValidatorContext);
//            return false;
//        }
//    }
//
//    private boolean digitValidation(String bankAccount, ConstraintValidatorContext constraintValidatorContext) {
//        if (bankAccount.matches(DIGIT_REGEX)) {
//            return true;
//        } else {
//            String message = "Bank account contains illegal characters. Only digits required=> " + bankAccount;
//            prepareMessage(message, constraintValidatorContext);
//            return false;
//        }
//    }

//    @Override
//    boolean isParamValid(String bankAccounts) {
//        if (!lengthValidation(ACCOUNT_LENGTH, bankAccounts, constraintValidatorContext)) {
//            prepareMessage(LENGTH_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        if (!digitValidation(bankAccounts, constraintValidatorContext)) {
//            prepareMessage(DIGIT_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        if (!checksumValidation(bankAccounts)) {
//            prepareMessage(CHECK_SUM_MESSAGE + bankAccounts, constraintValidatorContext);
//            return false;
//        }
//        return true;
//    }

    @Override
    boolean checksumValidation(String param) {
        IBANValidator validator = IBANValidator.DEFAULT_IBAN_VALIDATOR;
        return validator.isValid(IBAN_COUNTRY_CODE + param);
//        boolean isAccoutValid = validator.isValid(IBAN_COUNTRY_CODE + param);
//        if (isAccoutValid) {
//            return true;
//        } else {
//            prepareMessage(CHECK_SUM_MESSAGE+param, constraintValidatorContext);
//            return false;
//        }
    }

//    private boolean IBANValidation(String bankAccount, ConstraintValidatorContext constraintValidatorContext) {
//        IBANValidator validator = IBANValidator.DEFAULT_IBAN_VALIDATOR;
//        boolean isAccoutValid = validator.isValid(IBAN_COUNTRY_CODE + bankAccount);
//        if (isAccoutValid) {
//            return true;
//        } else {
//            String message = "Bank account number is not valid. => " + bankAccount;
//            prepareMessage(message, constraintValidatorContext);
//            return false;
//        }
//    }

//    private void prepareMessage(String message, ConstraintValidatorContext constraintValidatorContext) {
//        constraintValidatorContext
//                .buildConstraintViolationWithTemplate(message)
//                .addConstraintViolation();
//    }
}
