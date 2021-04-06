package com.sda.onlinestore.restController;

import com.sda.onlinestore.dto.AddressDto;
import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.service.RoleService;
import com.sda.onlinestore.transformers.RoleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/userAccount/role")
public class RoleController {

    private final RoleService roleService;

    private final RoleTransformer roleTransformer;

    @Autowired
    public RoleController(RoleService roleService, RoleTransformer roleTransformer) {
        this.roleService = roleService;
        this.roleTransformer = roleTransformer;
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        Role role = roleTransformer.transform(roleDto);
        Role savedRole = roleService.saveRole(role);
        RoleDto savedRoleDto = roleTransformer.transformReversed(savedRole);
        return ResponseEntity.ok(savedRoleDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getRoles();
        return ResponseEntity.ok(roleDtoList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleDto> findRolesById(@PathVariable("id") Long id) {
        Role role = roleService.findRoleById(id);
        RoleDto roleDto = roleTransformer.transformReversed(role);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto){
        Role role = roleTransformer.transform(roleDto);
        Role savedRole = roleService.saveRole(role);
        RoleDto savedRoleDto = roleTransformer.transformReversed(savedRole);
        return ResponseEntity.ok(savedRoleDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable("id") Long id){
        roleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}