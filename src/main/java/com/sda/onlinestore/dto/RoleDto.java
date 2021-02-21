package com.sda.onlinestore.dto;

public class RoleDto {

    private String roleName;

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public RoleDto() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
