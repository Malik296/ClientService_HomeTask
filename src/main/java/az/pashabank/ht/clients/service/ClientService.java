package az.pashabank.ht.clients.service;

import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO findClientById(Long id) throws NullPointerException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client = optionalClient.orElseThrow(() -> new NullPointerException("Client not found"));
        logger.info("Client Id: {}", id);
        return entityToDTO(client);
    }

    public List<ClientDTO> getAllClient() {
        List<Client> list = clientRepository.findAll();
        List<ClientDTO> clientList = new ArrayList<>();

        for (Client client : list) {
            ClientDTO clientDTO =  entityToDTO(client);
            clientList.add(clientDTO);
        }
        return clientList;
    }

    public ClientDTO addClient(ClientDTO request) {
        Client client = new Client();
        client.setUpdateData(LocalDateTime.now());
        saveClient(client, request);
        logger.info("Client Add");
        return request;
    }

    public ClientDTO updateClient(ClientDTO request) throws NullPointerException {
        Optional<Client> optionalClient = clientRepository.findById(request.getId());
        Client client = optionalClient.orElseThrow(() -> new NullPointerException("Client not found"));

        client.setUpdateData(LocalDateTime.now());
        saveClient(client, request);
        logger.info("Client Update");
        return request;
    }

    public ClientDTO deleteClient(Long id) throws NullPointerException {
        ClientDTO clientDTO = findClientById(id);
        clientRepository.deleteById(id);
        logger.info("Client Delete");
        return clientDTO;
    }

    private ClientDTO entityToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setLastName(client.getLastName());
        return clientDTO;
    }

    private void saveClient(Client client, ClientDTO clientDTO) {
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        clientRepository.save(client);
    }
}
