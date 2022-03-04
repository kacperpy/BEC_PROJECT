package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.AddressCreateDto;
import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.dto.AddressReadDto;
import dk.bec.bookanything.mapper.AddressMapper;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<AddressReadDto> getAddress(@PathVariable("id") Long id) {
        Optional<AddressEntity> addressOptional = addressService.getAddressById(id);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressMapper.mapAddressEntityToDto(addressEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    ResponseEntity<AddressReadDto> createAddress(@Valid @RequestBody AddressCreateDto addressDto) {
        AddressEntity address = addressMapper.mapAddressDtoToEntity(addressDto, null);
        Optional<AddressEntity> addressOptional = addressService.createAddress(address);

        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressMapper.mapAddressEntityToDto(addressEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/{id}")
    ResponseEntity<AddressReadDto> updateAddress(@PathVariable("id") Long id, @Valid @RequestBody AddressCreateDto addressDto) {
        AddressEntity address = addressMapper.mapAddressDtoToEntity(addressDto, id);
        Optional<AddressEntity> addressOptional = addressService.updateAddress(id, address);

        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressMapper.mapAddressEntityToDto(addressEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<AddressDto> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);

        return addressService.getAddressById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }
}
