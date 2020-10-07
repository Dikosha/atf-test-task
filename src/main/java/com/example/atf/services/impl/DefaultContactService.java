package com.example.atf.services.impl;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.entities.ContactEntity;
import com.example.atf.models.entities.ContactTypeEntity;
import com.example.atf.models.jsons.ContactJson;
import com.example.atf.models.pojos.Client;
import com.example.atf.models.pojos.Contact;
import com.example.atf.models.pojos.ContactAll;
import com.example.atf.models.pojos.impl.DefaultClient;
import com.example.atf.models.pojos.impl.DefaultContact;
import com.example.atf.models.pojos.impl.DefaultContactAll;
import com.example.atf.repositories.ClientRepository;
import com.example.atf.repositories.ContactRepository;
import com.example.atf.repositories.ContactTypeRepository;
import com.example.atf.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultContactService implements ContactService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    public DefaultContactService() {
    }

    public DefaultContactService(ContactRepository contactRepository, ClientRepository clientRepository, ContactTypeRepository contactTypeRepository) {
        this.contactRepository = contactRepository;
        this.contactTypeRepository = contactTypeRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ContactAll> getAll() {
        List<ContactEntity> contactEntities = contactRepository.findAll();
        return contactEntities.stream().map(contactEntity -> DefaultContactAll.builder()
                .clientId(contactEntity.getContactClientId().getClientId())
                .clientName(contactEntity.getContactClientId().getClientName())
                .clientSurname(contactEntity.getContactClientId().getClientSurname())
                .clientPatronymic(contactEntity.getContactClientId().getClientPatronymic())
                .contactId(contactEntity.getContactId())
                .contactPhone(contactEntity.getContactPhone())
                .contactTypeId(contactEntity.getContactTypeId().getContactTypeId())
                .contactTypeName(contactEntity.getContactTypeId().getContactTypeName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<ContactTypeEntity> getTypes() {
        List<ContactTypeEntity> contactTypeEntities = contactTypeRepository.findAll();
        return contactTypeEntities;
    }

    @Override
    public Client getAllByUser(Long id) {
        ClientEntity clientEntity = clientRepository.findByClientId(id);
        List<ContactEntity> contactEntities = contactRepository.findAllByContactClientId(clientEntity);
        return DefaultClient.builder()
                .clientId(clientEntity.getClientId())
                .clientName(clientEntity.getClientName())
                .clientSurname(clientEntity.getClientSurname())
                .clientPatronymic(clientEntity.getClientPatronymic())
                .contacts(parseContacts(contactEntities)).build();
    }

    private List<Contact> parseContacts(List<ContactEntity> contactEntities){
        return contactEntities.stream().map(contactEntity -> DefaultContact.builder()
                .contactId(contactEntity.getContactId())
                .contactPhone(contactEntity.getContactPhone())
                .contactTypeId(contactEntity.getContactTypeId().getContactTypeId())
                .contactTypeName(contactEntity.getContactTypeId().getContactTypeName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public String add(ContactJson contactJson) {
        ContactTypeEntity contactTypeEntity = contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId());
        ClientEntity clientEntity = clientRepository.findByClientId(contactJson.getContactClientId());
        if(contactTypeEntity != null && clientEntity != null){
            ContactEntity contactEntity = new ContactEntity(
                    null,
                    contactJson.getContactPhone(),
                    clientEntity,
                    contactTypeEntity
                    );
            contactRepository.save(contactEntity);
            return "Added";
        }
        return "Client or contact type not found";
    }

    @Override
    public String update(ContactJson contactJson) {
        ContactTypeEntity contactTypeEntity = contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId());
        ClientEntity clientEntity = clientRepository.findByClientId(contactJson.getContactClientId());
        if(contactTypeEntity != null && clientEntity != null){
            ContactEntity contactEntity = contactRepository.findByContactId(contactJson.getContactId());
            contactEntity.setContactClientId(clientEntity);
            contactEntity.setContactPhone(contactJson.getContactPhone());
            contactEntity.setContactTypeId(contactTypeEntity);
            contactRepository.save(contactEntity);
            return "Updated";
        }
        return "Client or contact type not found";
    }

    @Override
    public String delete(Long id) {
        ContactEntity contactEntity = contactRepository.findByContactId(id);
        if(contactEntity != null){
            contactRepository.delete(contactEntity);
            return "Deleted";
        }
        return "Contact not found";
    }
}
