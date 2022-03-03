package dk.bec.bookanything.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class FutureDateTimeValidator implements ConstraintValidator<FutureDateTimeConstraint, LocalDateTime> {
    @Override
    public void initialize(FutureDateTimeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return localDateTime.isAfter(LocalDateTime.now());
    }
}
