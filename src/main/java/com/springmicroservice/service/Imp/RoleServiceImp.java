package com.springmicroservice.service.Imp;

import com.springmicroservice.dao.RoleDao;
import com.springmicroservice.domain.Role;
import com.springmicroservice.exception.ResourceNotFoundException;
import com.springmicroservice.service.RoleService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> listRole() {
        return roleDao.findAll();
    }

    @Override
    public ResponseEntity<Role> saveRole(Role role) {
        Optional<Role> roleSaved = roleDao.findByName(role.getName());
        if(roleSaved.isPresent()){
            throw new ResourceNotFoundException("El rol con ese nombre ya existe:"+role.getName());
        }else{
            return new ResponseEntity<>(roleDao.save(role), HttpStatus.OK);
        }
    }

    @Override
    public void deleteRole(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public Optional<Role> findRole(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public ResponseEntity<Role> updateRole(Long id, Role role) {
        return roleDao.findById(id)
                .map(roleSaved -> {
                    roleSaved.setName(role.getName());
                    roleSaved.setDescription(role.getDescription());
                    Role roleSave = roleDao.save(roleSaved);
                    return new ResponseEntity<>(roleSave, HttpStatus.OK);
                }).orElseGet(()->ResponseEntity.notFound().build());
    }
}
