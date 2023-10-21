package com.springmicroservice.service;

import com.springmicroservice.domain.Client;
import com.springmicroservice.domain.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> listClient();
    ResponseEntity<Client> saveClient(Client client);
    void deleteClient(Long id_client);
    Optional<Client> findClient(Long id_client);
    ResponseEntity<Client> updateClient(Long id,Client client);
}
