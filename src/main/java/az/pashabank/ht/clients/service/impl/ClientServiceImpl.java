package az.pashabank.ht.clients.service.impl;

import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.mapper.ClientMapper;
import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.repository.ClientRepository;
import az.pashabank.ht.clients.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO findClientById(Long id) throws NullPointerException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client = optionalClient.orElseThrow(() -> new NullPointerException("Client not found"));
        logger.info("Client Id: {}, {}", id);
        return ClientMapper.INSTANCE.toClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClient() {
        return ClientMapper.INSTANCE.toClientDTOs(clientRepository.findAll());
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.tOToClient(clientDTO);
        client.setCreateDate(LocalDateTime.now());
        clientDTO.setId(saveClient(client, clientDTO).getId());
        logger.info("Client Add");
        return clientDTO;
    }
    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) throws NullPointerException {
        Optional<Client> optionalClient = clientRepository.findById(clientDTO.getId());
        Client client = optionalClient.orElseThrow(() -> new NullPointerException("Client not found"));

        client.setUpdateDate(LocalDateTime.now());
        logger.info("Client Update");
        return ClientMapper.INSTANCE.toClientDTO(saveClient(client, clientDTO));
    }
    @Override
    public ClientDTO deleteClient(Long id) throws NullPointerException {
        ClientDTO clientDTO = findClientById(id);
        clientRepository.deleteById(id);
        logger.info("Client Delete");
        return clientDTO;
    }

    private Client saveClient(Client client, ClientDTO clientDTO) {
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        return clientRepository.save(client);
    }
}
