package az.pashabank.ht.clients.repository;

import az.pashabank.ht.clients.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    @Override
    List<Account> findAll();

    List<Account> findAllByClientId(Long id);
}
