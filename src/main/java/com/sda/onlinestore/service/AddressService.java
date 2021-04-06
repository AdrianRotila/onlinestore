package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.AddressRepository;
import com.sda.onlinestore.transformers.AddressTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    @Autowired
    private final AddressTransformer addressTransformer;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressTransformer addressTransformer) {
        this.addressRepository = addressRepository;
        this.addressTransformer = addressTransformer;
    }

    public void addAddress(AddressDto addressDto){
        Address address = addressTransformer.transform(addressDto);
        addressRepository.save(address);
    }

    public List<AddressDto> getAddresses(){
        List<Address> addresses = addressRepository.findAll();
        List<AddressDto> addressDtoList = new ArrayList<>();

        for(Address address : addresses){
            AddressDto addressDto = addressTransformer.transformReversed(address);
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address findAddresseById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            System.out.println(address.toString());
            return address;
        } else {
            System.out.println("Address with ID " + id + " does not exist.");
            throw new NotFoundException("Address with ID " + id + " does not exist.");
        }
    }

    public void deleteAddressById(Long id) {
        this.findAddresseById(id);
        addressRepository.deleteById(id);
    }
}
