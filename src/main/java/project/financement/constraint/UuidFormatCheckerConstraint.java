package project.financement.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import project.financement.annotation.UuidFormatChecker;

import java.util.Optional;
import java.util.regex.Pattern;

public class UuidFormatCheckerConstraint implements ConstraintValidator<UuidFormatChecker, String> {
    private final String pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    @Override
    public void initialize(UuidFormatChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext context) {
        if (uuid != null) {
            return Optional.of(uuid)
                    .filter(i -> !i.isBlank())
                    .map(el -> Pattern.compile(pattern).matcher(el).matches())
                    .orElse(false);
        }
        return false;
    }
}
