package com.springmicroservice.controller;

import com.springmicroservice.domain.Client;
import com.springmicroservice.domain.Role;
import com.springmicroservice.service.Imp.RoleServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


public class ControllerRoleTests {

    private RoleController roleController;
    private RoleServiceImp roleServiceImp;

    @BeforeEach
    public void setUp() {
        roleServiceImp = mock(RoleServiceImp.class);
        roleController = new RoleController(roleServiceImp);
    }
    @DisplayName("Test para obtener Lista de Roles")
    @Test
    public void getRoles() throws Exception {
        // Given
        List<Role> mockRoles = new ArrayList<>();
        mockRoles.add(Role.builder()
                .name("Cli Name All")
                .description("Cli Description All")
                .build());
        Mockito.when(roleServiceImp.listRole()).thenReturn(mockRoles);
        // When
        List<Role> response = roleController.getRoles();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0).getName()).isEqualTo("Cli Name All");
    }
    @DisplayName("Test para obtener Rol")
    @Test
    public void getRole() throws Exception {
        // Given
        Long roleId = 1L;
        Role mockRole = Role.builder()
                .name("Cli Name All")
                .description("Cli Description All")
                .build();
        Mockito.when(roleServiceImp.findRole(anyLong())).thenReturn(Optional.ofNullable(mockRole));
        // When
        Optional<Role> response = roleController.getRole(roleId);

        // Then
        assertThat(response).isNotNull();
        response.ifPresent(role -> assertThat(role.getName()).isEqualTo("Cli Name All"));
    }
    @DisplayName("Test para guardar Rol")
    @Test
    public void postRole() throws Exception {
        // Given
        Role newRole = Role.builder()
                .name("Cli Name Save")
                .description("Cli Description Save")
                .build();
        Role savedRole = Role.builder()
                .id(1L) // Simulamos que se ha guardado en la base de datos y se le ha asignado un ID
                .name(newRole.getName())
                .description(newRole.getDescription())
                .build();
        ResponseEntity<Role> responseEntity = ResponseEntity.ok(savedRole); // Configura ResponseEntity con el objeto Role
        Mockito.when(roleServiceImp.saveRole(any(Role.class))).thenReturn(responseEntity);
        // When
        ResponseEntity<Role> response = roleController.postRole(newRole);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Verifica que el estado sea OK
        assertThat(response.getBody()).isNotNull(); // Verifica que el cuerpo del ResponseEntity no sea nulo
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L); // Verificamos el ID del rol guardado
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Cli Name Save");
    }
    @DisplayName("Test para actualizar Rol")
    @Test
    public void putRole() throws Exception {
        // Given
        Long roleId = 1L; // ID del rol que deseas actualizar
        Role updatedRole = Role.builder()
                .id(roleId) // Simulamos que se ha actualizado en la base de datos
                .name("Cli Name Update")
                .description("Cli Description Update")
                .build();
        ResponseEntity<Role> responseEntity = ResponseEntity.ok(updatedRole); // Configura ResponseEntity con el objeto Role actualizado
        Mockito.when(roleServiceImp.updateRole(any(Long.class), any(Role.class))).thenReturn(responseEntity);

        // When
        ResponseEntity<Role> response = roleController.putRole(roleId, updatedRole);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Verifica que el estado sea OK
        assertThat(response.getBody()).isNotNull(); // Verifica que el cuerpo del ResponseEntity no sea nulo
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(roleId); // Verificamos el ID del rol actualizado
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Cli Name Update");
    }
    @DisplayName("Test para eliminar Rol")
    @Test
    public void testDeleteRole() throws Exception {
        // Given
        Long roleId = 1L; // ID del rol que deseas eliminar
        // Configura el mock para que el método deleteRole no haga nada (void)
        doNothing().when(roleServiceImp).deleteRole(roleId);

        // When
        ResponseEntity<String> response = roleController.del(roleId);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Verifica que el estado sea OK
        // Verifica que el método deleteRole se llamó con el ID correcto
        verify(roleServiceImp).deleteRole(roleId);
        assertThat(response.getBody()).isEqualTo("Role delete successfully"); // Verifica el mensaje de éxito
    }
}
