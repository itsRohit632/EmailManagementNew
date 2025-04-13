package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.model.Role;
import com.projectmanagement.model.Role.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
