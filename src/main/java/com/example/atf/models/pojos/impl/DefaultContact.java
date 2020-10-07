package com.example.atf.models.pojos.impl;

import com.example.atf.models.pojos.Contact;
import lombok.Builder;

@Builder
public class DefaultContact implements Contact {
    private Long contactId;
    private String contactPhone;
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
    public Long getContactTypeId() {
        return contactTypeId;
    }

    @Override
    public String getContactTypeName() {
        return contactTypeName;
    }
}
