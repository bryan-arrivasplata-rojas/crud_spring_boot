package com.springmicroservice.dao;

import com.springmicroservice.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client,Long> {
}
