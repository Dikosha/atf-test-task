package com.example.atf.models.pojos;

import java.util.List;

public interface Client {
    Long getClientId();
    String getClientName();
    String getClientSurname();
    String getClientPatronymic();
    List<Contact> getContact();
}
