package reclutamiento.app.api_proceso.resources.dtos.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphaNumericWithSpacesValidator
        implements ConstraintValidator<AlphaNumericWithSpaces, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }

        return value.matches("^[a-zA-Z0-9 ]+$");
    }
}
