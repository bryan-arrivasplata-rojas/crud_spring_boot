package com.springmicroservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    public void testId() {
        Client client = new Client();
        client.setId(1L);
        assertEquals(1L, client.getId());
    }

    @Test
    public void testName() {
        Client client = new Client();
        client.setName("Bryan");
        assertEquals("Bryan", client.getName());
    }

    @Test
    public void testLastname() {
        Client client = new Client();
        client.setLastname("Arrivasplata");
        assertEquals("Arrivasplata", client.getLastname());
    }

    @Test
    public void testEmail() {
        Client client = new Client();
        client.setEmail("bryan.arrivasplata@gmail.com");
        assertEquals("bryan.arrivasplata@gmail.com", client.getEmail());
    }

    @Test
    public void testNumber() {
        Client client = new Client();
        client.setNumber("997767771");
        assertEquals("997767771", client.getNumber());
    }

    @Test
    public void testRole() {
        Client client = new Client();
        Role role = new Role();
        client.setRole(role);
        assertEquals(role, client.getRole());
    }
}
