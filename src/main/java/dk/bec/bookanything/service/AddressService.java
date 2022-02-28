package dk.bec.bookanything.service;

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

    public Optional<AddressEntity> getAddressByUuid(UUID uuid){
        return addressRepository.findByUuid(uuid);
    }

    public Optional<AddressEntity> createAddress(AddressEntity address){
        return Optional.of(addressRepository.saveAndFlush(address));
    }

    public Optional<AddressEntity> updateAddress(AddressEntity address){
        return Optional.of(addressRepository.saveAndFlush(address));
    }

    public void deleteAddressByUuid(UUID uuid){
        addressRepository.deleteByUuid(uuid);
    }


}
