package com.springmicroservice.controller;

import com.springmicroservice.domain.Role;
import com.springmicroservice.service.Imp.RoleServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController{

    private final RoleServiceImp roleServiceImp;

    @Autowired
    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getRoles(){
        return roleServiceImp.listRole();
    }
    @GetMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Role> getRole(@PathVariable Long id) {
        return roleServiceImp.findRole(id);
    }
    @PostMapping("/role")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> postRole(@RequestBody Role role){
        return roleServiceImp.saveRole(role);
    }
    @PutMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Role> putRole(@PathVariable Long id, @RequestBody Role role){
        return roleServiceImp.updateRole(id,role);
    }
    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> del(@PathVariable Long id){
        roleServiceImp.deleteRole(id);
        return new ResponseEntity<String>("Role delete successfully",HttpStatus.OK);
    }

}
