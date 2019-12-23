package com.whiteslave.whiteslaveApp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull
@NotEmpty
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = BankAccountValidator.class)
@Documented
public @interface BankAccount {

    String message() default "Bank account parameter error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
