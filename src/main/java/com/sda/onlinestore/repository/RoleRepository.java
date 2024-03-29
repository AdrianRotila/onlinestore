package com.sda.onlinestore.repository;

import com.sda.onlinestore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    void getRoleByRoleName(String role);
}
