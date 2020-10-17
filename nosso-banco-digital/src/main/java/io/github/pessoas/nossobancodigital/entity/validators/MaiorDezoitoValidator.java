package io.github.pessoas.nossobancodigital.entity.validators;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaiorDezoitoValidator implements ConstraintValidator<MaiorDezoito, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(value, hoje);
        if(periodo.getYears() >= 18) {
            return true;
        }
        return false;
    }
    
}
