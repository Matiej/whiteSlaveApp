package com.whiteslave.whiteslaveApp.govRequestReport.validator;

import org.apache.commons.validator.routines.DateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateParamValidator implements ConstraintValidator<DateParam, String> {

    private final static String DATE_VALID_MESSAGE = "The date is not valid or syntax error=> ";
    private final static String DATE_FUTURE_MESSAGE = "The date you provided is from the future=> ";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private ConstraintValidatorContext constraintValidatorContext;

    @Override
    public void initialize(DateParam constraintAnnotation) {

    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        this.constraintValidatorContext = constraintValidatorContext;
        constraintValidatorContext.disableDefaultConstraintViolation();
        //todo zbjutifulizować to jakoś.
        if(!isDateValid(date)) {
            prepareMessage(DATE_VALID_MESSAGE+date);
            return false;
        }
        if(isDateFuture(date)) {
            prepareMessage(DATE_FUTURE_MESSAGE+date);
            return false;
        }
        return true;
    }

    private boolean isDateValid(String date) {
        DateValidator validator = DateValidator.getInstance();
        return validator.isValid(date, DATE_PATTERN);
    }

    private boolean isDateFuture(String date) {
        return LocalDate.parse(date).isAfter(LocalDate.now());
    }

    private void prepareMessage(String message) {
        constraintValidatorContext
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
