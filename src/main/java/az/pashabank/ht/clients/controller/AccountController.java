package az.pashabank.ht.clients.controller;

import az.pashabank.ht.clients.model.AccountDTO;
import az.pashabank.ht.clients.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> getAllAccount() {
        logger.info("Input Get Request");
        return accountService.getAllAccount();
    }

    @GetMapping("client-account/{id}")
    public List<AccountDTO> getAccountByClientId(@PathVariable Long id) {
        return accountService.getAccountByClientId(id);
    }

    @GetMapping("{id}")
    public AccountDTO getAccountById( @PathVariable Long id) throws NullPointerException {
        logger.info("Input Find Account Request: {}", id);
        return accountService.getAccountById(id);
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        logger.info("Input Post Request");
        return accountService.addAccount(accountDTO);
    }

    @PutMapping
    public AccountDTO updateAccount(@Valid @RequestBody AccountDTO accountDTO) throws NullPointerException {
        logger.info("Input Put Request (Update account)");
        return accountService.updateAccount(accountDTO);
    }

    @PutMapping("balance/")
    public AccountDTO balanceManipulation(@RequestBody AccountDTO accountDTO) {
        logger.info("Input Put Request(Manipulation balance)");
        return accountService.updateBalance(accountDTO);
    }

    @DeleteMapping("{id}")
    public AccountDTO deleteAccount(@PathVariable Long id) throws NullPointerException {
        logger.info("Input Delete Account Request: {}", id);
        return accountService.deleteAccount(id);
    }
}
