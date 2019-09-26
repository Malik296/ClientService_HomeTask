package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class ClientScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ClientScheduler.class);
    private static Random random = new Random();
    private final RandomClientGenerator randomClientGenerator;
    private final ClientRepository clientRepository;

    public ClientScheduler(RandomClientGenerator randomClientGenerator, ClientRepository clientRepository) {
        this.randomClientGenerator = randomClientGenerator;
        this.clientRepository = clientRepository;
    }

    @Scheduled(fixedDelay = 20000, initialDelay = 2000)
    public void generate() throws InterruptedException {
        int i = random.nextInt(3);
        for (int j = 0; j <= i; j++) {
            ClientDTO clientDTO = randomClientGenerator.nameGenerator();
            Client client = new Client();
            client.setName(clientDTO.getName());
            client.setLastName(clientDTO.getLastName());

            client.setCreateData(LocalDateTime.now());

            clientRepository.save(client);
            Thread.sleep(500);
            logger.info("New Client Register: [Name: {}, Last Name: {}]", clientDTO.getName(), clientDTO.getLastName());
        }
    }
}
