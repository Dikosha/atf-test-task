package com.example.atf.models.pojos.impl;

import com.example.atf.models.pojos.ContactAll;
import lombok.Builder;

@Builder
public class DefaultContactAll implements ContactAll {
    private Long contactId;
    private String contactPhone;
    private Long clientId;
    private String clientName;
    private String clientSurname;
    private String clientPatronymic;
    private Long contactTypeId;
    private String contactTypeName;

    @Override
    public Long getContactId() {
        return contactId;
    }

    @Override
    public String getContactPhone() {
        return contactPhone;
    }

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
    public Long getContactTypeId() {
        return contactTypeId;
    }

    @Override
    public String getContactTypeName() {
        return contactTypeName;
    }
}
