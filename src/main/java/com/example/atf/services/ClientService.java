package com.example.atf.services;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.jsons.ClientJson;

import java.util.List;

public interface ClientService {
    List<ClientEntity> getAll();
    String add(ClientJson clientJson);
    String update(ClientJson clientJson);
    String delete(Long id);
}
