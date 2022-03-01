package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.model.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public static AddressEntity mapAddressDtoToEntity(AddressDto addressDto, Long id){
        return AddressEntity.builder()
                .id(id)
                .street(addressDto.getStreet())
                .streetNumber(addressDto.getStreetNumber())
                .flatNumber(addressDto.getFlatNumber())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .country(addressDto.getCountry())
                .build();
    }

    public static AddressDto mapAddressEntityToDto(AddressEntity addressEntity){
        return AddressDto.builder()
                .street(addressEntity.getStreet())
                .streetNumber(addressEntity.getStreetNumber())
                .flatNumber(addressEntity.getFlatNumber())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .country(addressEntity.getCountry())
                .build();
    }
}
