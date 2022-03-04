package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.AddressCreateDto;
import dk.bec.bookanything.dto.AddressReadDto;
import dk.bec.bookanything.model.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity mapAddressDtoToEntity(AddressCreateDto addressDto, Long id) {
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

    public AddressReadDto mapAddressEntityToDto(AddressEntity addressEntity) {
        return AddressReadDto.builder()
                .id(addressEntity.getId())
                .street(addressEntity.getStreet())
                .streetNumber(addressEntity.getStreetNumber())
                .propertyNumber(addressEntity.getPropertyNumber())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .country(addressEntity.getCountry())
                .build();
    }
}
