package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.entity.UserAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserAccountTransformer {

    public UserAccount transform(UserAccountDto userAccountDto){

        Address address = new Address();
        BeanUtils.copyProperties(userAccountDto.getAddress(), address);

        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountDto, userAccount);

        userAccount.setAddress(address);
        return userAccount;
    }

    public UserAccountDto transformReversed(UserAccount userAccount){
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(userAccount.getAddress(), addressDto);

        UserAccountDto userAccountDto = new UserAccountDto();
        BeanUtils.copyProperties(userAccount, userAccountDto);

        userAccountDto.setAddress(addressDto);
        return userAccountDto;
    }
}
