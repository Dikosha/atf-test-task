package com.example.atf.services.impl;

import com.example.atf.models.entities.ClientEntity;
import com.example.atf.models.jsons.ClientJson;
import com.example.atf.repositories.ClientRepository;
import com.example.atf.repositories.ContactTypeRepository;
import com.example.atf.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultClientService implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public DefaultClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public DefaultClientService() {

    }

    @Override
    public List<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public String add(ClientJson clientJson) {
        ClientEntity clientEntity = new ClientEntity(
                null,
                clientJson.getClientName(),
                clientJson.getClientSurname(),
                clientJson.getClientPatronymic());
        clientRepository.save(clientEntity);
        return "Added";
    }

    @Override
    public String update(ClientJson clientJson) {
        ClientEntity clientEntity = clientRepository.findByClientId(clientJson.getClientId());
        if(clientEntity != null){
            clientEntity.setClientName(clientJson.getClientName());
            clientEntity.setClientSurname(clientJson.getClientSurname());
            clientEntity.setClientPatronymic(clientJson.getClientPatronymic());
            clientRepository.save(clientEntity);
            return "Updated";
        }
        return "Client not found";
    }

    @Override
    public String delete(Long clientId) {
        ClientEntity clientEntity = clientRepository.findByClientId(clientId);
        if(clientEntity != null){
            clientRepository.delete(clientEntity);
            return "Deleted";
        }
        return "Client not found";
    }
}
