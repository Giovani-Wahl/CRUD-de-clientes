package com.giovaniwahl.CRUDdeclientes.services;

import com.giovaniwahl.CRUDdeclientes.dtos.ClientDTO;
import com.giovaniwahl.CRUDdeclientes.entities.Client;
import com.giovaniwahl.CRUDdeclientes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow();
        return new ClientDTO(client);
    }
}
