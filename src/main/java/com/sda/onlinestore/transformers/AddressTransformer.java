package com.sda.onlinestore.transformers;
import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {

    public Address transform(AddressDto addressDto){
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return address;
    }

    public AddressDto transformReversed(Address address){
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address, addressDto);
        return addressDto;
    }
}
