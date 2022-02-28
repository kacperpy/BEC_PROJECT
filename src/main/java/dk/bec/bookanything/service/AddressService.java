package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<AddressEntity> getAddressById(Long id){
        return addressRepository.findById(id);
    }

    public Optional<AddressEntity> createAddress(AddressEntity address){
        return Optional.of(addressRepository.save(address));
    }

    public Optional<AddressEntity> updateAddress(Long id, AddressEntity newAddress){
        return addressRepository.findById(id).map(address -> {
            address.setStreet(newAddress.getStreet());
            address.setCity(newAddress.getCity());
            address.setStreetNumber(newAddress.getStreetNumber());
            address.setFlatNumber(newAddress.getFlatNumber());
            address.setPostalCode(newAddress.getPostalCode());
            address.setCountry(newAddress.getCountry());
            return addressRepository.save(address);
        });
    }

    public void deleteAddressById(Long id){
        addressRepository.deleteById(id);
    }


}
