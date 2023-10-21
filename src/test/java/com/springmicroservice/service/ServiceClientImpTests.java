package com.springmicroservice.service;

import com.springmicroservice.domain.Client;
import com.springmicroservice.service.Imp.ClientServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ServiceClientImpTests {

    @Autowired
    ClientServiceImp clientServiceImp;

    @DisplayName("Test para Listar Clientes")
    @Test
    @Transactional
    void listClients(){
        //given
        Client client = Client.builder()
                .name("Cli Name")
                .lastname("Cli Lastname")
                .email("Cli Email").
                number("Cli Number").
                build();
        Client client1 = Client.builder()
                .name("Cli Name1")
                .lastname("Cli Lastname1")
                .email("Cli Email1").
                number("Cli Number1").
                build();
        //When
        clientServiceImp.saveClient(client);
        clientServiceImp.saveClient(client1);
        //Then
        List<Client> listClients = clientServiceImp.listClient();
        Assertions.assertThat(listClients).isNotNull();
        Assertions.assertThat(listClients.size()).isEqualTo(2);
    }

    @DisplayName("Test para guardar client")
    @Test
    @Transactional
    void saveClient(){
        //given
        Client client1 = Client.builder()
                .name("Cli Name Save")
                .lastname("Cli Lastname Save")
                .email("Cli Email Save").
                number("Cli Number Save").
                build();
        //When
        ResponseEntity<Client> clientsaved = clientServiceImp.saveClient(client1);
        //Then
        Assertions.assertThat(clientsaved).isNotNull();
        Assertions.assertThat(clientsaved.getBody().getId()).isGreaterThan(0);
    }
    @DisplayName("Test para cliente duplicado al guardar client")
    @Test
    @Transactional
    void saveClientDuplicate(){
        //given
        Client client1 = Client.builder()
                .name("Cli Name Find")
                .lastname("Cli Lastname Find")
                .email("Cli Email Find").
                number("Cli Number Find").
                build();
        clientServiceImp.saveClient(client1);
        //When
        Optional<Client> clientOptional = clientServiceImp.findClient(client1.getId());
        //Then
        Assertions.assertThat(clientOptional).isNotEmpty();
    }
    @DisplayName("Test para eliminar client")
    @Test
    @Transactional
    void deleteClient(){
        //given
        Client client1 = Client.builder()
                .name("Cli Name Delete")
                .lastname("Cli Lastname Delete")
                .email("Cli Email Delete").
                number("Cli Number Delete").
                build();
        clientServiceImp.saveClient(client1);
        //When
        clientServiceImp.deleteClient(client1.getId());
        Optional<Client> clientOptional = clientServiceImp.findClient(client1.getId());
        //Then
        Assertions.assertThat(clientOptional).isEmpty();
    }
    @DisplayName("Test para encontrar client")
    @Test
    @Transactional
    void findClient(){
        //given
        Client client1 = Client.builder()
                .name("Cli Name Find")
                .lastname("Cli Lastname Find")
                .email("Cli Email Find").
                number("Cli Number Find").
                build();
        clientServiceImp.saveClient(client1);
        //When
        Optional<Client> clientOptional = clientServiceImp.findClient(client1.getId());
        //Then
        Assertions.assertThat(clientOptional).isNotEmpty();
    }
    @DisplayName("Test para actualizar client")
    @Test
    @Transactional
    void updateClient() {
        //given
        Client client1 = Client.builder()
                .name("Cli Name Upd")
                .lastname("Cli Lastname Upd")
                .email("Cli Email Upd").
                number("Cli Number Upd").
                build();
        clientServiceImp.saveClient(client1);
        //When
        Client clientToUpdate = clientServiceImp.findClient(client1.getId()).get();

        clientToUpdate.setName("Cli Name Update");
        clientToUpdate.setLastname("Cli Lastname Upd Update");
        clientToUpdate.setEmail("Cli Email Update");
        clientToUpdate.setNumber("Cli Number Update");
        ResponseEntity<Client> clientUpdated = clientServiceImp.updateClient(client1.getId(), clientToUpdate);
        //Then
        Client clientBody = clientUpdated.getBody();
        if (clientBody != null) {
            Assertions.assertThat(clientBody.getName()).isEqualTo("Cli Name Update");
            Assertions.assertThat(clientBody.getLastname()).isEqualTo("Cli Lastname Upd Update");
            Assertions.assertThat(clientBody.getEmail()).isEqualTo("Cli Email Update");
            Assertions.assertThat(clientBody.getNumber()).isEqualTo("Cli Number Update");
        }
    }
}
