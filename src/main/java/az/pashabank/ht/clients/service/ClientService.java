package az.pashabank.ht.clients.service;

import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final DataModel dataModel;

    public ClientService(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public ClientDTO findClientForId(String id) {
        try {
            if (!dataModel.getMap().containsKey(Integer.parseInt(id))) {
                throw new NumberFormatException();
            }
            int i = Integer.parseInt(id);
            return dataModel.getMap().get(i);
        } catch (NumberFormatException e) {
            logger.error("Client Not Found: " + id);
            throw new NumberFormatException("Client Not Found: " + id);
        }
    }

    public Collection<ClientDTO> getAllClient() {
        logger.info("Get Request ");
        return dataModel.getMap().values();
    }

    public ClientDTO addClient(ClientDTO request) {

        dataModel.setLastId(dataModel.getLastId() + 1);
        request.setId(dataModel.getLastId());
        dataModel.getMap().put(dataModel.getLastId(), request);
        ClientDTO clientDTO = dataModel.getMap().get(dataModel.getLastId());

        logger.info("Add Client Request ");
        return clientDTO;
    }

    public ClientDTO updateClient(ClientDTO request) {
        logger.info("Update Request ");
        if (request.getId() > dataModel.getLastId() || !dataModel.getMap().containsKey(request.getId())) {
            logger.error("Client Not Found: ");
            throw new NumberFormatException("Client Not Found for Update");
        }
        ClientDTO clientDTO = dataModel.getMap().get(request.getId());
        dataModel.getMap().put(request.getId(), request);

        return clientDTO;
    }

    public ClientDTO deleteClient(String id) {
        logger.info("Delete Request ");
        try {
            if (!dataModel.getMap().containsKey(Integer.parseInt(id))) {
                 throw new NumberFormatException();
            }
            ClientDTO clientDTO = dataModel.getMap().get(Integer.parseInt(id));
            dataModel.getMap().remove(Integer.parseInt(id));
            return clientDTO;
        } catch (NumberFormatException e) {
            logger.error("Id Not Found: " + id);
            throw new NumberFormatException("Id Not Found: " + id);
        }
    }
}
