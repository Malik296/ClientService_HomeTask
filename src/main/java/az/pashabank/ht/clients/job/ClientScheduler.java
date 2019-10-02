package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.entity.Account;
import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.mapper.AccountMapper;
import az.pashabank.ht.clients.mapper.ClientMapper;
import az.pashabank.ht.clients.model.AccountDTO;
import az.pashabank.ht.clients.model.ClientDTO;
import az.pashabank.ht.clients.repository.AccountRepository;
import az.pashabank.ht.clients.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class ClientScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ClientScheduler.class);
    private static Random random = new Random();
    private final RandomClientGenerator randomClientGenerator;
    private final RandomAccountGenerate randomAccountGenerate;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public ClientScheduler(RandomClientGenerator randomClientGenerator, RandomAccountGenerate randomAccountGenerate, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.randomClientGenerator = randomClientGenerator;
        this.randomAccountGenerate = randomAccountGenerate;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    @Scheduled(fixedDelay = 20000, initialDelay = 2000)
    public void generate() throws InterruptedException {
        int i = random.nextInt(3);
        for (int j = 0; j <= i; j++) {
            ClientDTO clientDTO = randomClientGenerator.nameGenerator();
            Client client = saveClient(clientDTO);
            int n = random.nextInt(3);
            for (int k = 0; k <= n; k++) {
                AccountDTO accountDTO = randomAccountGenerate.accountGenerate(clientDTO.getName());
                saveAccount(accountDTO, client);
            }

            Thread.sleep(500);
            logger.info("New Client Register: [Name: {}, Last Name: {}, Accounts: {}]", clientDTO.getName(), clientDTO.getLastName(), n + 1);
        }
    }

    private void saveAccount(AccountDTO accountDTO, Client client) {
        Account account = AccountMapper.INSTANCE.toAccount(accountDTO);
        account.setCreateDate(LocalDateTime.now());
        account.setBalance(BigDecimal.valueOf(random.nextInt(9)*100));
        account.setClient(client);
        accountRepository.save(account);
    }


    private Client saveClient(ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.tOToClient(clientDTO);
        client.setCreateDate(LocalDateTime.now());
        return clientRepository.save(client);
    }
}
