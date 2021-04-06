package com.sda.onlinestore.mvcController;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AddressMVCController {
    private final AddressService addressService;

    @Autowired
    public AddressMVCController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping("/api/addAddress")
    public ResponseEntity addAddress(@RequestBody AddressDto addressDto){
        addressService.addAddress(addressDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/getAddresses")
    public ResponseEntity getAddresses(){
        List<AddressDto> addressDtoList = addressService.getAddresses();
        return ResponseEntity.ok(addressDtoList);
    }


}


