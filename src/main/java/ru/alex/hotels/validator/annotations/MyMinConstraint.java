package ru.alex.hotels.validator.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.alex.hotels.validator.MyMinValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {MyMinValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMinConstraint {
    String message() default "Значение не может быть отрицательным";

    int min();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
