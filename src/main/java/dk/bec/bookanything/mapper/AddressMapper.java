package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.model.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity mapAddressDtoToEntity(AddressDto addressDto, Long id){
        return AddressEntity.builder()
                .id(id)
                .street(addressDto.getStreet())
                .streetNumber(addressDto.getStreetNumber())
                .propertyNumber(addressDto.getPropertyNumber())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .country(addressDto.getCountry())
                .build();
    }

    public AddressDto mapAddressEntityToDto(AddressEntity addressEntity){
        return AddressDto.builder()
                .street(addressEntity.getStreet())
                .streetNumber(addressEntity.getStreetNumber())
                .propertyNumber(addressEntity.getPropertyNumber())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .country(addressEntity.getCountry())
                .build();
    }
}
