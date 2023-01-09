package com.food.marcosfood.core.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class MutiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numeroMultiplo;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.numeroMultiplo = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;
        if (number != null) {
            var valorDecimal = BigDecimal.valueOf(number.doubleValue());
            var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
            var resto = valorDecimal.remainder(multiploDecimal);

            valido = BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return valido;
    }

}
