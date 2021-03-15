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
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountDto, userAccount);
        return userAccount;
    }

    public UserAccountDto transformReversed(UserAccount userAccount){
        UserAccountDto userAccountDto = new UserAccountDto();
        BeanUtils.copyProperties(userAccount, userAccountDto);
        return userAccountDto;
    }
}
