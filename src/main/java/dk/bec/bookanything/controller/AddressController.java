package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses/{id}")
    ResponseEntity<AddressDto> getAddress(@PathVariable("id") Long id) {
        Optional<AddressEntity> addressOptional = addressService.getAddressById(id);
        Optional<AddressDto> addressDtoOptional = addressOptional.map(AddressDto::new);

        return addressDtoOptional.map(addressDto -> new ResponseEntity<>(addressDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addresses")
    ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressEntity address = new AddressEntity(addressDto);
        Optional<AddressEntity> addressOptional = addressService.createAddress(address);

        return addressOptional.map(addressEntity -> new ResponseEntity<>(new AddressDto(addressEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/addresses/{id}")
    ResponseEntity<AddressDto> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDto addressDto) {
        AddressEntity address = new AddressEntity(addressDto);
        Optional<AddressEntity> addressOptional = addressService.updateAddress(id, address);

        return addressOptional.map(addressEntity -> new ResponseEntity<>(new AddressDto(addressEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/addresses/{id}")
    ResponseEntity<AddressDto> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);

        return addressService.getAddressById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }

}
