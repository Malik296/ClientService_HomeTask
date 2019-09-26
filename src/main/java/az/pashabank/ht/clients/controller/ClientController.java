package az.pashabank.ht.clients.controller;

import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClient() {
        logger.info("Input Get Request");
        return clientService.getAllClient();
    }

    @GetMapping("{id}")
    public ClientDTO getClientForId( @PathVariable Long id) throws NullPointerException {
        logger.info("Input Find Client Request: {}", id);
        return clientService.findClientById(id);
    }

    @PostMapping
    public ClientDTO create(@RequestBody @Valid ClientDTO request) {
        logger.info("Input Post Request");
        return clientService.addClient(request);
    }

    @PutMapping
    public ClientDTO update(@Valid @RequestBody ClientDTO request) throws NullPointerException {
        logger.info("Input Put Request");
        return clientService.updateClient(request);
    }

    @DeleteMapping("{id}")
    public ClientDTO delete(@PathVariable Long id) throws NullPointerException {
        logger.info("Input Delete Request: {}", id);
        return clientService.deleteClient(id);
    }

}
