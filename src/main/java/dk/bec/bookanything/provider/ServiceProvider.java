package dk.bec.bookanything.provider;

import dk.bec.bookanything.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceProvider {

    AddressService addressService;
    BookableObjectService bookableObjectService;
    DayOpenService dayOpenService;
    DiscountCodeService discountCodeService;
    FacilityTypeService facilityTypeService;
    FacilityService facilityService;
    FeatureService featureService;
    ReservationService reservationService;
    RoleService roleService;
    UserService userService;

    @Autowired
    public ServiceProvider(AddressService addressService, BookableObjectService bookableObjectService, DayOpenService dayOpenService, DiscountCodeService discountCodeService, FacilityTypeService facilityTypeService, FacilityService facilityService, FeatureService featureService, ReservationService reservationService, RoleService roleService, UserService userService) {
        this.addressService = addressService;
        this.bookableObjectService = bookableObjectService;
        this.dayOpenService = dayOpenService;
        this.discountCodeService = discountCodeService;
        this.facilityTypeService = facilityTypeService;
        this.facilityService = facilityService;
        this.featureService = featureService;
        this.reservationService = reservationService;
        this.roleService = roleService;
        this.userService = userService;
    }

}
