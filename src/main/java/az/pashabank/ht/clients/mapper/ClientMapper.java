package az.pashabank.ht.clients.mapper;

import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.model.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper  {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toClientDTO(Client client);

    List<ClientDTO> toClientDTOs(List<Client> clients);

    Client tOToClient(ClientDTO clientDTO);
}
