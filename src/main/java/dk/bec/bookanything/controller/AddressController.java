package dk.bec.bookanything.controller;

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
    ResponseEntity<AddressEntity> get(@PathVariable("id") Long id) {
        Optional<AddressEntity> addressOptional = addressService.getAddressById(id);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addresses")
    ResponseEntity<AddressEntity> add(@RequestBody AddressEntity address) {
        Optional<AddressEntity> addressOptional = addressService.createAddress(address);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/addresses")
    ResponseEntity<AddressEntity> update(@RequestBody AddressEntity address) {
        Optional<AddressEntity> addressOptional = addressService.updateAddress(address);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/addresses/{id}")
    ResponseEntity<AddressEntity> delete(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);
        return addressService.getAddressById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }


}
