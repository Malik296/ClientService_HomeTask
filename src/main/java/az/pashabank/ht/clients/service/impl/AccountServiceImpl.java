package az.pashabank.ht.clients.service.impl;

import az.pashabank.ht.clients.entity.Account;
import az.pashabank.ht.clients.entity.Client;
import az.pashabank.ht.clients.mapper.AccountMapper;
import az.pashabank.ht.clients.model.AccountDTO;
import az.pashabank.ht.clients.repository.AccountRepository;
import az.pashabank.ht.clients.repository.ClientRepository;
import az.pashabank.ht.clients.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<AccountDTO> getAllAccount() {
        return AccountMapper.INSTANCE.toAccountDTOs(accountRepository.findAll());
    }

    @Override
    public List<AccountDTO> getAccountByClientId(Long id) {
        List<Account> list = accountRepository.findAllByClientId(id);
        if (list.size() == 0) {
            throw new NullPointerException("Client or Account not found");
        }
        return AccountMapper.INSTANCE.toAccountDTOs(list);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Account account = optionalAccount.orElseThrow(() -> new NullPointerException("Account not found"));
        logger.info("Account Id: {}", id);
        return AccountMapper.INSTANCE.toAccountDTO(account);
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        Optional<Client> optionalClient = clientRepository.findById(accountDTO.getClientId());
        Client client = optionalClient.orElseThrow(() -> new NullPointerException("Client Not Found"));
        Account account = new Account();
        account.setClient(client);
        account.setUpdateDate(LocalDateTime.now());
        accountDTO.setId(saveAccount(account, accountDTO).getId());
        logger.info("Account Add");
        return accountDTO;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountDTO.getId());
        Account account = optionalAccount.orElseThrow(() -> new NullPointerException("Account not found"));

        account.setUpdateDate(LocalDateTime.now());
        logger.info("Account Update");
        return AccountMapper.INSTANCE.toAccountDTO(saveAccount(account, accountDTO));
    }

    @Override
    public AccountDTO deleteAccount(Long id) {
        AccountDTO accountDTO = getAccountById(id);
        accountRepository.deleteById(id);
        logger.info("Account Delete");
        return accountDTO;
    }

    @Override
    public AccountDTO updateBalance(AccountDTO accountDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(accountDTO.getId());
        Account account = optionalAccount.orElseThrow(() -> new NullPointerException("Account not found"));
        AccountDTO accountDtoDb = AccountMapper.INSTANCE.toAccountDTO(account);

        if (accountDTO.getPassword().equals(accountDtoDb.getPassword())) {
            accountDtoDb.setBalance(accountDtoDb.getBalance().add(accountDTO.getBalance()));
            if (accountDtoDb.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
                account.setBalance(accountDtoDb.getBalance());
            } else {
                throw new IllegalArgumentException("Balance is not enough.");
            }
            account.setUpdateDate(LocalDateTime.now());
            accountRepository.save(account);
        } else {
            //TODO add logger
            throw new NullPointerException("The username or password is incorrect.");
        }
        return accountDtoDb;
    }

    private Account saveAccount(Account account, AccountDTO accountDTO) {
        account.setPassword(accountDTO.getPassword());
        return accountRepository.save(account);
    }
}
