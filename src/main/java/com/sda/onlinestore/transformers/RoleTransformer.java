package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleTransformer {

    public Role transform(RoleDto roleDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        return role;
    }

    public RoleDto transformReversed (Role role){
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
}
