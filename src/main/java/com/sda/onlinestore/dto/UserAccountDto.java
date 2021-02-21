package com.sda.onlinestore.dto;

import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.enums.PreferredChannel;
import com.sda.onlinestore.entity.Role;

public class UserAccountDto {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private AddressDto addressDto;
    private RoleDto role;
    private String thumbnail;

    public UserAccountDto(Long id, AddressDto addressDto, String email, String password, String thumbnail, String confirmPassword, String username, RoleDto role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.thumbnail = thumbnail;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.addressDto = addressDto;
        this.role = role;
    }

    public UserAccountDto() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }


    public AddressDto getAddress() {
        return addressDto;
    }

    public void setAddress(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
