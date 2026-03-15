package reclutamiento.app.api_proceso.resources.dtos.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphaNumericWithSpacesValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaNumericWithSpaces {

    String message() default "Solo se permiten letras, numeros y espacios";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}