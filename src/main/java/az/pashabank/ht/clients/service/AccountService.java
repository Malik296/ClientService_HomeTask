package az.pashabank.ht.clients.service;

import az.pashabank.ht.clients.model.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount();

    List<AccountDTO> getAccountByClientId(Long id);

    AccountDTO getAccountById(Long id);

    AccountDTO addAccount(AccountDTO accountDTO);

    AccountDTO updateAccount(AccountDTO accountDTO);

    AccountDTO deleteAccount(Long id);

    AccountDTO updateBalance(AccountDTO accountDTO);
}
