package com.giovaniwahl.CRUDdeclientes.services;

import com.giovaniwahl.CRUDdeclientes.dtos.ClientDTO;
import com.giovaniwahl.CRUDdeclientes.entities.Client;
import com.giovaniwahl.CRUDdeclientes.repositories.ClientRepository;
import com.giovaniwahl.CRUDdeclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Id Not Found."));
        return new ClientDTO(client);
    }
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDTO::new);
    }
    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
       Client client = new Client();
       copyDtoToEntity(clientDTO,client);
       client =clientRepository.save(client);
       return new ClientDTO(client);

    }
    @Transactional
    public ClientDTO update(Long id,ClientDTO clientDTO){
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(clientDTO,client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found!");
        }
    }
    @Transactional
    public void delete(long id){
        if (!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found !");
        }
       clientRepository.deleteById(id);
    }
    private void copyDtoToEntity(ClientDTO clientDTO, Client client){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }
}
