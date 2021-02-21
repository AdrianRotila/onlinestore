package com.sda.onlinestore.mvcController;

import com.sda.onlinestore.service.RoleService;
import com.sda.onlinestore.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleMVCController {

    private final RoleService roleService;

    @Autowired
    public RoleMVCController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/viewRole")
    public String viewRole(){
        return "role-list";
    }
}
