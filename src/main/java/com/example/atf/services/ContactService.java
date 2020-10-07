package com.example.atf.services;

import com.example.atf.models.entities.ContactTypeEntity;
import com.example.atf.models.jsons.ContactJson;
import com.example.atf.models.pojos.Client;
import com.example.atf.models.pojos.ContactAll;

import java.util.List;

public interface ContactService {
    List<ContactAll> getAll();
    List<ContactTypeEntity> getTypes();
    Client getAllByUser(Long id);
    String add(ContactJson contactJson);
    String update(ContactJson contactJson);
    String delete(Long id);
}
