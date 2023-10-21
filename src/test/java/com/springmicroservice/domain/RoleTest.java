package com.springmicroservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {
    @Test
    public void testId() {
        Role role = new Role();
        role.setId(1L);
        assertEquals(1L, role.getId());
    }

    @Test
    public void testName() {
        Role role = new Role();
        role.setName("Admin");
        assertEquals("Admin", role.getName());
    }

    @Test
    public void testDescription() {
        Role role = new Role();
        role.setDescription("Administrator Role");
        assertEquals("Administrator Role", role.getDescription());
    }
}
