package ru.alex.hotels.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.alex.hotels.validations.annotations.MyMinConstraint;

//Просто попробовал создать свою аннотацию для валидации
public class MyMinValidator implements ConstraintValidator<MyMinConstraint, Integer> {
    private int min;
    @Override
    public void initialize(MyMinConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer > min;
    }

}
