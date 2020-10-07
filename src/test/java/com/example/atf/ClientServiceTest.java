package com.example.atf;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.jsons.ClientJson;
import com.example.atf.repositories.ClientRepository;
import com.example.atf.services.ClientService;
import com.example.atf.services.impl.DefaultClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private DefaultClientService clientService;

    @BeforeEach
    void initUseCase() {
        clientService = new DefaultClientService(clientRepository);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void getAll(){
        ClientJson clientJson = new ClientJson();
        clientJson.setClientId(1L);
        clientJson.setClientName("Илияс");
        clientJson.setClientSurname("Туреханов");
        clientJson.setClientPatronymic("Галымжанович");
        ClientEntity clientEntity = new ClientEntity(null, clientJson.getClientName(), clientJson.getClientSurname(), clientJson.getClientPatronymic());
        when(clientRepository.findAll()).thenReturn(Arrays.asList(clientEntity));
        List<ClientEntity> clientEntities = clientService.getAll();
        assertThat(clientEntities).hasSize(1);
    }

    @Test
    void add(){
        ClientJson clientJson = new ClientJson();
        clientJson.setClientName("Илияс");
        clientJson.setClientSurname("Туреханов");
        clientJson.setClientPatronymic("Галымжанович");

        ClientEntity clientEntity = new ClientEntity(null, clientJson.getClientName(), clientJson.getClientSurname(), clientJson.getClientPatronymic());
        String result = clientService.add(clientJson);

        assertEquals(result, "Added");

        Mockito.verify(clientRepository, Mockito.times(1)).save(clientEntity);
    }

    @Test
    void update(){
        ClientJson clientJson = new ClientJson();
        clientJson.setClientId(1L);
        clientJson.setClientName("Илияс");
        clientJson.setClientSurname("Туреханов");
        clientJson.setClientPatronymic("Галымжанович");
        ClientEntity clientEntity = new ClientEntity(null, clientJson.getClientName(), clientJson.getClientSurname(), clientJson.getClientPatronymic());

        doReturn(clientEntity).when(clientRepository).findByClientId(1L);
        String result = clientService.update(clientJson);

        assertEquals(result, "Updated");

        Mockito.verify(clientRepository, Mockito.times(1)).save(clientEntity);
    }

    @Test
    void updateFail(){
        ClientJson clientJson = new ClientJson();
        clientJson.setClientId(1L);
        clientJson.setClientName("Илияс");
        clientJson.setClientSurname("Туреханов");
        clientJson.setClientPatronymic("Галымжанович");

        String result = clientService.update(clientJson);

        assertEquals(result, "Client not found");
    }

    @Test
    void delete(){
        ClientEntity clientEntity = new ClientEntity(null, "Диас", "Абдуалиев", "Галымжанович");

        doReturn(clientEntity).when(clientRepository).findByClientId(1L);

        String result = clientService.delete(1L);

        assertEquals(result, "Deleted");
    }

    @Test
    void deleteFail(){
        String result = clientService.delete(1L);

        assertEquals(result, "Client not found");
    }

}
