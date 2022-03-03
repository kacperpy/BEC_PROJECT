package dk.bec.bookanything.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureDateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureDateTimeConstraint {
    String message() default "DateTime must be in the future";
}
