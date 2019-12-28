package com.whiteslave.whiteslaveApp.validator;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.validator.routines.IBANValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class BankAccountValidator extends GovNumbersSharedValidator implements ConstraintValidator<BankAccount, String> {

    private final static String LENGTH_MESSAGE = "Bank account number has incorrect length. Required 26 characters => ";
    private final static String DIGIT_MESSAGE = "Bank account contains illegal characters. Only digits required=> ";
    private final static String CHECK_SUM_MESSAGE = "Bank account number checksum is not valid. => ";
    private final static String IBAN_COUNTRY_CODE = "PL";
    private final static String ACCOUNT_SEPARATOR = ",";
    private final static int ACCOUNT_LENGTH = 26;

    @Override
    public void initialize(BankAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(String bankAccounts, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Map<String, String> messageMap = ImmutableMap
                .of("LENGTH_MESSAGE", LENGTH_MESSAGE,
                        "DIGIT_MESSAGE", DIGIT_MESSAGE,
                        "CHECK_SUM_MESSAGE", CHECK_SUM_MESSAGE);
        return paramsValidateDistributor(bankAccounts,constraintValidatorContext, messageMap, ACCOUNT_LENGTH);
    }

    @Override
    boolean checksumValidation(String param) {
        IBANValidator validator = IBANValidator.DEFAULT_IBAN_VALIDATOR;
        return validator.isValid(IBAN_COUNTRY_CODE + param);
    }
}
