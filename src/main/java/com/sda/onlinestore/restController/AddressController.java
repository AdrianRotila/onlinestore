package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.service.AddressService;
import com.sda.onlinestore.transformers.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/userAccount/address")
public class AddressController {

    private final AddressService addressService;

    private final AddressTransformer addressTransformer;

    @Autowired
    public AddressController(AddressService addressService, AddressTransformer addressTransformer) {
        this.addressService = addressService;
        this.addressTransformer = addressTransformer;
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        Address address = addressTransformer.transform(addressDto);
        Address savedAddress = addressService.saveAddress(address);
        AddressDto savedAddressDto = addressTransformer.transformReversed(savedAddress);
        return ResponseEntity.ok(savedAddressDto);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addressDtoList = addressService.getAddresses();
        return ResponseEntity.ok(addressDtoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AddressDto> findAddressesById(@PathVariable("id") Long id) {
        Address address = addressService.findAddresseById(id);
        AddressDto addressDto = addressTransformer.transformReversed(address);
        return ResponseEntity.ok(addressDto);
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto) {
        Address address = addressTransformer.transform(addressDto);
        Address savedAddress = addressService.saveAddress(address);
        AddressDto savedAddressDto = addressTransformer.transformReversed(savedAddress);
        return ResponseEntity.ok(savedAddressDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }
}
