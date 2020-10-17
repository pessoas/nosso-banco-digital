package io.github.pessoas.nossobancodigital.entity.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaiorDezoitoValidator.class)
@Documented
public @interface MaiorDezoito {
String message() default "{MaiorDezoito.invalid}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };
}
