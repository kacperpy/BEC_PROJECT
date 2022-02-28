package dk.bec.bookanything.controller;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses/{uuid}")
    ResponseEntity<AddressEntity> getAddress(@PathVariable("uuid") UUID uuid) {
        Optional<AddressEntity> addressOptional = addressService.getAddressByUuid(uuid);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addresses")
    ResponseEntity<AddressEntity> createAddress(@RequestBody AddressEntity address) {
        Optional<AddressEntity> addressOptional = addressService.createAddress(address);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/addresses")
    ResponseEntity<AddressEntity> updateAddress(@RequestBody AddressEntity address) {
        Optional<AddressEntity> addressOptional = addressService.updateAddress(address);
        return addressOptional.map(addressEntity -> new ResponseEntity<>(addressEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/addresses/{uuid}")
    ResponseEntity<AddressEntity> deleteAddress(@PathVariable("uuid") UUID uuid) {
        addressService.deleteAddressByUuid(uuid);
        return addressService.getAddressByUuid(uuid).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }

}
