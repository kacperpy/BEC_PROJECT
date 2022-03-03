package dk.bec.bookanything.validator;

import dk.bec.bookanything.provider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ForeignKeyExistsValidator implements ConstraintValidator<ForeignKeyExistsConstraint, Long> {

    private final ServiceProvider serviceProvider;
    private Class<?> serviceClass;

    @Autowired
    public ForeignKeyExistsValidator(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void initialize(ForeignKeyExistsConstraint constraintAnnotation) {
        serviceClass = constraintAnnotation.serviceClass();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        String className = serviceClass.getName().split("\\.")[4];

        switch (className) {
            case "AddressService":
                return serviceProvider.getAddressService().getAddressById(id).isPresent();
            case "BookableObjectService":
                return serviceProvider.getBookableObjectService().getBookableObjectById(id).isPresent();
            case "DayOpenService":
                return serviceProvider.getDayOpenService().getDayOpenById(id).isPresent();
            case "DiscountCodeService":
                return serviceProvider.getDiscountCodeService().getDiscountCode(id).isPresent();
            case "FacilityTypeService":
                return serviceProvider.getFacilityTypeService().getFacilityTypeById(id).isPresent();
            case "FacilityService":
                return serviceProvider.getFacilityService().getFacilityById(id).isPresent();
            case "FeatureService":
                return serviceProvider.getFeatureService().getFeatureById(id).isPresent();
            case "ReservationService":
                return serviceProvider.getReservationService().getReservationById(id).isPresent();
            case "RoleService":
                return serviceProvider.getRoleService().getRole(id).isPresent();
            case "UserService":
                return serviceProvider.getUserService().getUserById(id).isPresent();
        }
        return false;
    }
}
