package dk.bec.bookanything.validator;

import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ForeignKeyExistsValidator implements ConstraintValidator<ForeignKeyExistsConstraint, Long> {

    private final FacilityTypeService facilityTypeService;

    @Autowired
    public ForeignKeyExistsValidator(FacilityTypeService facilityTypeService) {
        this.facilityTypeService = facilityTypeService;
    }

    @Override
    public void initialize(ForeignKeyExistsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(facilityTypeService.getFacilityTypeById(id) != null);
        return facilityTypeService.getFacilityTypeById(id) != null;
    }
}
