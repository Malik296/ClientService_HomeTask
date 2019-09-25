package az.pashabank.ht.clients.controller;

import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public Collection<ClientDTO> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping("{id}")
    public ClientDTO getClientForId( @PathVariable String id) {

        return clientService.findClientForId(id);
    }

    @PostMapping
    public ClientDTO create(@RequestBody @Valid ClientDTO request) {
        System.out.println(request.getName());
        return clientService.addClient(request);
    }

    @PutMapping
    public ClientDTO update(@RequestBody ClientDTO request) {
        return clientService.updateClient(request);
    }

    @DeleteMapping("{id}")
    public ClientDTO delete(@PathVariable String id) {
        return clientService.deleteClient(id);
    }

}
