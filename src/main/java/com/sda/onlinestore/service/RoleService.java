package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.RoleDto;
import com.sda.onlinestore.entity.Role;
import com.sda.onlinestore.repository.RoleRepository;
import com.sda.onlinestore.transformers.RoleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleTransformer roleTransformer;

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
//    @PostConstruct
//    public void init() {
//        this.createDefaultDoctors();
//    }
//
//    private void createDefaultDoctors() {
//        // create doctors only if they don't exist
//        List<Doctor> doctors = new ArrayList<>();
//        if(this.doctorRepository.count() == 0) {
//
//            doctors.add(new Doctor("Adrian", "Bobocel", "Str. Carpenului", 12l, "500412", "a.bobocel@gmail.com"));
//            doctors.add(new Doctor("Adrian", "Rotila", "Str. Socului", 45l, "500435", "a.rotila@gmail.com"));
//            doctors.add(new Doctor("Bogdan", "Gabor", "Str. Nucului", 5l, "500987", "bogdan.gabor@yahoo.com"));
//            doctors.add(new Doctor("Constantin", "Juncu", "Str. Ciresului", 59l, "500654", "c.juncu@yahoo.com"));
//            doctors.add(new Doctor("George", "Niculae", "Str. Calea Bucuresti", 255l, "500487", "g.nc12@gmail.com"));
//        }
//        doctorRepository.saveAll(doctors);
//    }
}
