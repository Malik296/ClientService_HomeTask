package az.pashabank.ht.clients.repository;

import az.pashabank.ht.clients.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    @Override
    List<Client> findAll();
}
