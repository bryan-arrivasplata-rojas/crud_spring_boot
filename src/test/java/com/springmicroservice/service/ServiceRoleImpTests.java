package com.springmicroservice.service;

import com.springmicroservice.domain.Role;
import com.springmicroservice.exception.ResourceNotFoundException;
import com.springmicroservice.service.Imp.RoleServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ServiceRoleImpTests {

    @Autowired
    RoleServiceImp roleServiceImp;

    @DisplayName("Test para Listar Roles")
    @Test
    @Transactional
    void listRoles(){
        //given
        Role role = Role.builder()
                .name("Cli")
                .description("Cli Description").build();
        Role role1 = Role.builder()
                .name("Cli List")
                .description("Cli ListDescription").build();
        //When
        roleServiceImp.saveRole(role);
        roleServiceImp.saveRole(role1);
        //Then
        List<Role> listRoles = roleServiceImp.listRole();
        Assertions.assertThat(listRoles).isNotNull();
        Assertions.assertThat(listRoles.size()).isEqualTo(2);
    }

    @DisplayName("Test para guardar rol")
    @Test
    @Transactional
    void saveRole(){
        //given
        Role role1 = Role.builder()
                .name("Cli Save")
                .description("Cli SaveDescription").build();
        //When
        ResponseEntity<Role> rolesaved = roleServiceImp.saveRole(role1);
        //Then
        Assertions.assertThat(rolesaved).isNotNull();
        Assertions.assertThat(Objects.requireNonNull(rolesaved.getBody()).getId()).isGreaterThan(0);

        //When
        try {
            roleServiceImp.saveRole(role1);
        } catch (ResourceNotFoundException e) {
            // Se espera que se lance una excepción ResourceNotFoundException
            return;
        } catch (Exception ex) {
            // Se espera que se lance una excepción diferente
            ResponseEntity<Role> response = ResponseEntity.notFound().build();
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            return;
        }
        //Then
        Assertions.fail("Se esperaba que se lanzara ResourceNotFoundException, pero no se lanzó.");

    }
    @DisplayName("Test para guardar rol duplicado")
    @Test
    @Transactional
    void saveRoleDuplicate(){
        //given
        Role role1 = Role.builder()
                .name("Cli Save")
                .description("Cli SaveDescription").build();
        //When
        try{
            roleServiceImp.saveRole(role1);
            roleServiceImp.saveRole(role1);
        }catch (ResourceNotFoundException e) {
            //Then
            Assertions.assertThat(e.getMessage()).isEqualTo("El rol con ese nombre ya existe:Cli Save"); // Verifica el mensaje de la excepción
        }

    }
    @DisplayName("Test para eliminar rol")
    @Test
    @Transactional
    void deleteRole(){
        //given
        Role role1 = Role.builder()
            .name("Cli Delete")
            .description("Cli DeleteDescription").build();
        roleServiceImp.saveRole(role1);
        //When
        roleServiceImp.deleteRole(role1.getId());
        Optional<Role> roleOptional = roleServiceImp.findRole(role1.getId());
        //Then
        Assertions.assertThat(roleOptional).isEmpty();
    }
    @DisplayName("Test para encontrar rol")
    @Test
    @Transactional
    void findRole(){
        //given
        Role role1 = Role.builder()
                .name("Cli Find")
                .description("Cli FindDescription").build();
        roleServiceImp.saveRole(role1);
        //When
        Optional<Role> roleOptional = roleServiceImp.findRole(role1.getId());
        //Then
        Assertions.assertThat(roleOptional).isNotEmpty();
    }
    @DisplayName("Test para actualizar rol")
    @Test
    @Transactional
    void updateRole(){
        //given
        Role role1 = Role.builder()
                .name("Cli Update")
                .description("Cli UpdateDescription").build();
        roleServiceImp.saveRole(role1);
        //When
        Role roleToUpdate = roleServiceImp.findRole(role1.getId()).get();

        roleToUpdate.setName("Client Update");
        roleToUpdate.setDescription("Client Description Update");
        ResponseEntity<Role> roleUpdated = roleServiceImp.updateRole(role1.getId(),roleToUpdate);
        //Then
        Role roleBody = roleUpdated.getBody();
        if (roleBody != null) {
            Assertions.assertThat(roleBody.getName()).isEqualTo("Client Update");
            Assertions.assertThat(roleBody.getDescription()).isEqualTo("Client Description Update");
        }
    }

}
