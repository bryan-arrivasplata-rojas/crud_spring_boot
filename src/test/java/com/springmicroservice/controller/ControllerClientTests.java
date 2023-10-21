package com.springmicroservice.controller;

import com.springmicroservice.domain.Client;
import com.springmicroservice.domain.Role;
import com.springmicroservice.service.Imp.ClientServiceImp;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class ControllerClientTests {

    private ClientController clientController;
    private ClientServiceImp clientServiceImp;

    @BeforeEach
    public void setUp() {
        clientServiceImp = mock(ClientServiceImp.class);
        clientController = new ClientController(clientServiceImp);
    }

    @DisplayName("Test para obtener Lista de Clientes")
    @Test
    public void getClients() throws Exception {
        // Given
        List<Client> mockClient = new ArrayList<>();
        Role roleTest = Role.builder().id(1L).build();
        mockClient.add(Client.builder().
                name("Bryan").
                lastname("Arrivasplata").
                email("bryan@gmail.com").
                number("997767771")
                .build());
        mockClient.add(Client.builder().
                name("Jersson").
                lastname("Arrivasplata").
                email("jersson@gmail.com").
                number("980501414")
                .build());
        Mockito.when(clientServiceImp.listClient()).thenReturn(mockClient);
        // When
        List<Client> response = clientController.getClients();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getName()).isEqualTo("Bryan");
    }
    @DisplayName("Test para obtener Cliente")
    @Test
    public void getClient() throws Exception {
        // Given
        Long clientId = 1L; // ID del cliente que deseas buscar
        Client mockClient = Client.builder()
                .name("Bryan")
                .lastname("Arrivasplata")
                .email("bryan@gmail.com")
                .number("997767771")
                .build();
        Mockito.when(clientServiceImp.findClient(anyLong())).thenReturn(Optional.ofNullable(mockClient));

        // When
        Optional<Client> response = clientController.getClient(clientId);

        // Then
        assertThat(response).isNotNull();
        response.ifPresent(client -> assertThat(client.getName()).isEqualTo("Bryan"));
    }
    @DisplayName("Test para guardar Cliente")
    @Test
    public void postClient() throws Exception {
        // Configura el comportamiento de saveClient en clientServiceImp
        Client newClient = Client.builder()
                .name("Client Name Save")
                .lastname("Client Lastname Save")
                .email("client@example.com")
                .number("1234567890")
                .build();
        Client savedClient = Client.builder()
                .id(1L) // Simulamos que se ha guardado en la base de datos y se le ha asignado un ID
                .name(newClient.getName())
                .lastname(newClient.getLastname())
                .email(newClient.getEmail())
                .number(newClient.getNumber())
                .build();
        ResponseEntity<Client> responseEntity = ResponseEntity.ok(savedClient);
        Mockito.when(clientServiceImp.saveClient(any(Client.class))).thenReturn(responseEntity);

        // Llama al método del controlador
        ResponseEntity<Client> response = clientController.postClient(newClient);

        // Verifica el resultado
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(1L);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Client Name Save");
    }
    @DisplayName("Test para actualizar Cliente")
    @Test
    public void putClient() throws Exception {
        // Configura el comportamiento de updateClient en clientServiceImp
        Long clientId = 1L; // ID del cliente que deseas actualizar
        Client updatedClient = Client.builder()
                .id(clientId)
                .name("Client Name Update")
                .lastname("Client Lastname Update")
                .email("updated@example.com")
                .number("9876543210")
                .build();
        ResponseEntity<Client> responseEntity = ResponseEntity.ok(updatedClient);
        Mockito.when(clientServiceImp.updateClient(any(Long.class), any(Client.class))).thenReturn(responseEntity);

        // Llama al método del controlador
        ResponseEntity<Client> response = clientController.putClient(clientId, updatedClient);

        // Verifica el resultado
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(clientId);
        assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Client Name Update");
    }

    @DisplayName("Test para eliminar Cliente")
    @Test
    public void deleteClient() throws Exception {
        // Configura el comportamiento de deleteClient en clientServiceImp
        Long clientId = 1L; // ID del cliente que deseas eliminar
        doNothing().when(clientServiceImp).deleteClient(clientId);

        // Llama al método del controlador
        ResponseEntity<String> response = clientController.del(clientId);

        // Verifica el resultado
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(clientServiceImp).deleteClient(clientId);
        assertThat(response.getBody()).isEqualTo("Client delete successfully");
    }
}
