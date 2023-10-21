package com.springmicroservice.controller;

import com.springmicroservice.domain.Client;
import com.springmicroservice.domain.Role;
import com.springmicroservice.service.Imp.ClientServiceImp;
import com.springmicroservice.service.Imp.RoleServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController{

    private final ClientServiceImp clientServiceImp;

    @Autowired
    public ClientController(ClientServiceImp clientServiceImp) {
        this.clientServiceImp = clientServiceImp;
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients(){
        return clientServiceImp.listClient();
    }
    @GetMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Client> getClient(@PathVariable Long id) {
        return clientServiceImp.findClient(id);
    }
    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Client> postClient(@RequestBody Client client){
        Role defaultRole = new Role();
        defaultRole.setId(1L);
        client.setRole(defaultRole);
        return clientServiceImp.saveClient(client);
    }
    @PutMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Client> putClient(@PathVariable Long id, @RequestBody Client client){
        return clientServiceImp.updateClient(id,client);
    }
    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> del(@PathVariable Long id){
        clientServiceImp.deleteClient(id);
        return new ResponseEntity<String>("Client delete successfully",HttpStatus.OK);
    }

}
