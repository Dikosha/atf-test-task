package com.example.atf.models.pojos.impl;

import com.example.atf.models.pojos.Client;
import com.example.atf.models.pojos.Contact;
import lombok.Builder;

import java.util.List;

@Builder
public class DefaultClient implements Client {

    private Long clientId;
    private String clientName, clientSurname, clientPatronymic;
    private List<Contact> contacts;

    @Override
    public Long getClientId() {
        return clientId;
    }

    @Override
    public String getClientName() {
        return clientName;
    }

    @Override
    public String getClientSurname() {
        return clientSurname;
    }

    @Override
    public String getClientPatronymic() {
        return clientPatronymic;
    }

    @Override
    public List<Contact> getContact() {
        return contacts;
    }
}
