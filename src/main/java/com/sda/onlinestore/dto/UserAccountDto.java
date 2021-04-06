package com.sda.onlinestore.dto;

import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.enums.PreferredChannel;
import com.sda.onlinestore.entity.Role;

import javax.validation.constraints.NotBlank;

public class UserAccountDto extends BaseEntityDto{

    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private AddressDto addressDto;
    private RoleDto roleDto;
    private String thumbnail;

    public UserAccountDto(AddressDto addressDto, String email, String password, String thumbnail, String confirmPassword, String username, RoleDto roleDto) {

        this.email = email;
        this.password = password;
        this.thumbnail = thumbnail;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.addressDto = addressDto;
        this.roleDto = roleDto;
    }

    public UserAccountDto() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
