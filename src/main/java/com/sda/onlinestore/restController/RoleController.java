package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/api/addRole")
    public ResponseEntity addRole(@RequestBody RoleDto roleDto){
        roleService.addRole(roleDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/getRoles")
    public ResponseEntity getRoles(){
        List<RoleDto> roleDtoList = roleService.getRoles();
        return ResponseEntity.ok(roleDtoList);
    }
}
