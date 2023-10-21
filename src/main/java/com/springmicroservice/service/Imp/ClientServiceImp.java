package com.springmicroservice.service.Imp;

import com.springmicroservice.dao.ClientDao;
import com.springmicroservice.domain.Client;
import com.springmicroservice.domain.Role;
import com.springmicroservice.exception.ResourceNotFoundException;
import com.springmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {
    @Autowired
    private ClientDao clientDao;
    @Override
    public List<Client> listClient() {
        return clientDao.findAll();
    }

    @Override
    public ResponseEntity<Client> saveClient(Client client) {
        return new ResponseEntity<>(clientDao.save(client), HttpStatus.OK);
    }

    @Override
    public void deleteClient(Long id_client) {
        clientDao.deleteById(id_client);
    }

    @Override
    public Optional<Client> findClient(Long id_client) {
        return clientDao.findById(id_client);
    }

    @Override
    public ResponseEntity<Client> updateClient(Long id,Client client) {
        return clientDao.findById(id)
                .map(clientSaved -> {
                    clientSaved.setName(client.getName());
                    clientSaved.setLastname(client.getLastname());
                    clientSaved.setLastname(client.getLastname());
                    clientSaved.setLastname(client.getLastname());

                    clientSaved.setLastname(client.getLastname());
                    Client clientSave = clientDao.save(clientSaved);
                    return new ResponseEntity<>(clientSave, HttpStatus.OK);
                }).orElseGet(()->ResponseEntity.notFound().build());
    }
}
