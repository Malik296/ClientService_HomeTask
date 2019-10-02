package az.pashabank.ht.clients.mapper

import az.pashabank.ht.clients.model.ClientDTO
import spock.lang.Specification

class ClientMapperTest extends Specification {

    void mapperTest() {
        setup:
        def clientDTO = new ClientDTO()
        clientDTO.setId(1)
        clientDTO.setName("Malik")
        clientDTO.setLastName("Khasammadov")

        when:
        def client = ClientMapper.INSTANCE.tOToClient(clientDTO)

        then:
        clientDTO.id == client.id
        clientDTO.name == client.name
        clientDTO.lastName == client.lastName
    }
}