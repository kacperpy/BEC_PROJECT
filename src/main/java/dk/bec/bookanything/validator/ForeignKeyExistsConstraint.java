package dk.bec.bookanything.validator;

import org.hibernate.service.Service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ForeignKeyExistsValidator.class)
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKeyExistsConstraint {
    String message() default "Foreign key doesn't exist in this context";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> serviceClass();

}
