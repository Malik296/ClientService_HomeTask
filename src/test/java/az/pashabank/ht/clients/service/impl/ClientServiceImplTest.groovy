package az.pashabank.ht.clients.service.impl

import az.pashabank.ht.clients.entity.Client
import az.pashabank.ht.clients.model.ClientDTO
import az.pashabank.ht.clients.repository.ClientRepository
import spock.lang.Specification

class ClientServiceImplTest extends Specification {
    def clientRepository = [
            save      : { Client entity ->
                entity.setId(1)
                return entity
            },
            findById  : { long id ->
                def entity = new Client()
                entity.setId(1)
                entity.setName("Malik")
                entity.setLastName("Khasammadov")
                return id == 1 ? Optional.of(entity) : null
            },
            findAll   : {
                def list = new ArrayList<Client>()
                def client = new Client()
                client.setName("first")
                list.add(client)

                client = new Client()
                client.setName("second")
                list.add(client)

                client = new Client()
                client.setName("third")
                list.add(client)

                return list
            },
            deleteById: { long id ->
                return null
            }

    ] as ClientRepository

    def clientService = new ClientServiceImpl(clientRepository)

    def "FindClientById"() {
        setup:
        def id = 1

        when:
        def clientDTO = clientService.findClientById(id)

        then:
        clientDTO.name == "Malik"
        clientDTO.lastName == "Khasammadov"
    }

    def "GetAllClient"() {
        setup:

        when:
        def listDTO = new ArrayList<ClientDTO>(clientService.getAllClient())

        then:
        listDTO[0].name == "first"
        listDTO[1].name == "second"
        listDTO[2].name == "third"
    }

    def "AddClient"() {
        setup:
        def clientDTO = new ClientDTO()
        clientDTO.setName("Malik")
        clientDTO.setLastName("Khasammadov")

        when:
        def clientDTO2 = clientService.addClient(clientDTO)

        then:
        clientDTO2.getId() > 0
    }

    def "DeleteClient"() {
        setup:

        when:
        def clientDTO = clientService.deleteClient(1)

        then:
        clientDTO.name == "Malik"
        clientDTO.lastName == "Khasammadov"
    }

    def "UpdateClient" () {
        setup:
        def clientDTO = new ClientDTO()
        clientDTO.setId(1)
        clientDTO.setName("First")
        clientDTO.setLastName("Last")

        when:
        def clientUpdate = clientService.updateClient(clientDTO)

        then:
        clientUpdate.name == "First"
        clientUpdate.lastName == "Last"

    }
}
