package com.example.atf;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.entities.ContactEntity;
import com.example.atf.models.entities.ContactTypeEntity;
import com.example.atf.models.jsons.ContactJson;
import com.example.atf.models.pojos.Client;
import com.example.atf.models.pojos.ContactAll;
import com.example.atf.repositories.ClientRepository;
import com.example.atf.repositories.ContactRepository;
import com.example.atf.repositories.ContactTypeRepository;
import com.example.atf.services.impl.DefaultContactService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ContactTypeRepository contactTypeRepository;

    @InjectMocks
    private DefaultContactService contactService;

    @BeforeEach
    void initUseCase() {
        contactService = new DefaultContactService(contactRepository, clientRepository, contactTypeRepository);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void getAll(){

        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");
        ContactEntity contactEntity = new ContactEntity(null, "+77475900709", clientEntity, contactTypeEntity);

        when(contactRepository.findAll()).thenReturn(Arrays.asList(contactEntity));

        List<ContactAll> contacts = contactService.getAll();

        assertThat(contacts).hasSize(1);
    }

    @Test
    void getAllByUser(){

        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");
        ContactEntity contactEntity = new ContactEntity(null, "+77475900709", clientEntity, contactTypeEntity);

        when(clientRepository.findByClientId(1L)).thenReturn(clientEntity);
        when(contactRepository.findAllByContactClientId(clientEntity)).thenReturn(Arrays.asList(contactEntity));

        Client client = contactService.getAllByUser(1L);

        assertThat(client.getContact()).hasSize(1);
    }

    @Test
    void getTypes(){
        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        when(contactTypeRepository.findAll()).thenReturn(Arrays.asList(contactTypeEntity));
        List<ContactTypeEntity> contactTypeEntities = contactService.getTypes();
        assertThat(contactTypeEntities).hasSize(1);
    }

    @Test
    void add(){
        ContactJson contactJson = new ContactJson();
        contactJson.setContactClientId(1L);
        contactJson.setContactPhone("+77475900709");
        contactJson.setContactTypeId(1L);
        contactJson.setContactId(1L);

        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");

        when(contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId())).thenReturn(contactTypeEntity);
        when(clientRepository.findByClientId(contactJson.getContactClientId())).thenReturn(clientEntity);

        String result = contactService.add(contactJson);
        assertEquals(result, "Added");
    }

    @Test
    void addFail(){
        ContactJson contactJson = new ContactJson();
        contactJson.setContactClientId(1L);
        contactJson.setContactPhone("+77475900709");
        contactJson.setContactTypeId(1L);
        contactJson.setContactId(1L);

        when(contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId())).thenReturn(null);
        when(clientRepository.findByClientId(contactJson.getContactClientId())).thenReturn(null);

        String result = contactService.add(contactJson);
        assertEquals(result, "Client or contact type not found");
    }


    @Test
    void update(){
        ContactJson contactJson = new ContactJson();
        contactJson.setContactClientId(1L);
        contactJson.setContactPhone("+77475900709");
        contactJson.setContactTypeId(1L);
        contactJson.setContactId(1L);

        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");
        ContactEntity contactEntity = new ContactEntity(null, "+77475900709", clientEntity, contactTypeEntity);

        when(contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId())).thenReturn(contactTypeEntity);
        when(clientRepository.findByClientId(contactJson.getContactClientId())).thenReturn(clientEntity);
        when(contactRepository.findByContactId(contactJson.getContactClientId())).thenReturn(contactEntity);

        String result = contactService.update(contactJson);
        assertEquals(result, "Updated");
    }

    @Test
    void updateFail(){
        ContactJson contactJson = new ContactJson();
        contactJson.setContactClientId(1L);
        contactJson.setContactPhone("+77475900709");
        contactJson.setContactTypeId(1L);
        contactJson.setContactId(1L);

        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");
        ContactEntity contactEntity = new ContactEntity(null, "+77475900709", clientEntity, contactTypeEntity);

        when(contactTypeRepository.findByContactTypeId(contactJson.getContactTypeId())).thenReturn(null);
        when(clientRepository.findByClientId(contactJson.getContactClientId())).thenReturn(null);

        String result = contactService.update(contactJson);
        assertEquals(result, "Client or contact type not found");
    }

    @Test
    void delete(){
        ContactTypeEntity contactTypeEntity = new ContactTypeEntity(null, "Name");
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");
        ContactEntity contactEntity = new ContactEntity(null, "+77475900709", clientEntity, contactTypeEntity);

        when(contactRepository.findByContactId(1L)).thenReturn(contactEntity);

        String result = contactService.delete(1L);
        assertEquals(result, "Deleted");
    }

    @Test
    void deleteFail(){
        when(contactRepository.findByContactId(1L)).thenReturn(null);

        String result = contactService.delete(1L);
        assertEquals(result, "Contact not found");
    }
}
