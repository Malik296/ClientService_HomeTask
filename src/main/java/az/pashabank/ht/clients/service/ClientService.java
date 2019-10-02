package az.pashabank.ht.clients.service;

import az.pashabank.ht.clients.model.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClient();

    ClientDTO findClientById(Long id);

    ClientDTO addClient(ClientDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    ClientDTO deleteClient(Long id);
}
