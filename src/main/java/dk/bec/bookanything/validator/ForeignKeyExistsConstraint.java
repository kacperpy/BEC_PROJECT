package dk.bec.bookanything.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ForeignKeyExistsValidator.class)
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKeyExistsConstraint {
    String message() default "Foreign key doesn't exist in this context";
    Class<?> serviceClass();

}
