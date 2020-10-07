package com.example.atf.repositories;

import com.example.atf.models.entities.ContactTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, Long> {
    ContactTypeEntity findByContactTypeId(Long id);
}
