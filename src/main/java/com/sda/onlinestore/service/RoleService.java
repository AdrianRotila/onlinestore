package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.entity.Address;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.RoleRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import com.sda.onlinestore.transformers.RoleTransformer;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private RoleTransformer roleTransformer;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(RoleDto roleDto) {
        Role role = roleTransformer.transform(roleDto);
        roleRepository.save(role);
    }

    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();

        for (Role role : roles) {
            RoleDto roleDto = roleTransformer.transformReversed(role);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }

    @PostConstruct
    private void postConstruct() {
        if (getRoles().isEmpty()) {
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");
            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role findRoleById(Long id) {
        Optional<Role> optRole = roleRepository.findById(id);
        if (optRole.isPresent()) {
            Role role = optRole.get();
            System.out.println(role.toString());
            return role;
        } else {
            System.out.println("Role with ID " + id + " does not exist.");
            throw new NotFoundException("Role with ID " + id + " does not exist.");
        }
    }

    public void deleteRoleById(Long id){
        this.findRoleById(id);
        roleRepository.deleteById(id);
    }

}











