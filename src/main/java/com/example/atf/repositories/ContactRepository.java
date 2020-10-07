package com.example.atf.repositories;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.entities.ContactEntity;
import com.example.atf.models.entities.ContactTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findAllByContactClientId(ClientEntity clientEntity);
    ContactEntity findByContactId(Long id);
}
