package dk.bec.bookanything.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return Optional.of(addressRepository.saveAndFlush(address));
    }

    public Optional<AddressEntity> updateAddress(AddressEntity address){
        return Optional.of(addressRepository.saveAndFlush(address));
    }

    public void deleteAddressById(Long id){
        addressRepository.deleteById(id);
    }


}
