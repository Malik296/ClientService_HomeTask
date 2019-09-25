package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.model.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClientScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ClientScheduler.class);

    private final RandomClientGenerator randomClientGenerator;
    private final DataModel dataModel;
    private ClientDTO clientDTO;

    @Autowired
    public ClientScheduler(RandomClientGenerator randomClientGenerator, DataModel dataModel, ClientDTO clientDTO) {
        this.randomClientGenerator = randomClientGenerator;
        this.dataModel = dataModel;
        this.clientDTO = clientDTO;
    }

    @Scheduled(fixedDelay = 20000, initialDelay = 2000)
    public void generate() {
        dataModel.setLastId(dataModel.getLastId() + 1);
        clientDTO = randomClientGenerator.nameGenerator();
        clientDTO.setId(dataModel.getLastId());
        dataModel.getMap().put(dataModel.getLastId(), clientDTO);

        logger.info("New Client Register: " + clientDTO.toString());
    }
}
