package com.springmicroservice.service;

import com.springmicroservice.domain.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> listRole();
    ResponseEntity<Role> saveRole(Role role);
    void deleteRole(Long id);
    Optional<Role> findRole(Long id);
    ResponseEntity<Role> updateRole(Long id,Role role);
}
