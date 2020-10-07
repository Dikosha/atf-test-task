package com.example.atf.repositories;

import com.example.atf.models.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByClientId(Long id);

}
