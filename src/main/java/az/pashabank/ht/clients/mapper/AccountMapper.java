package az.pashabank.ht.clients.mapper;

import az.pashabank.ht.clients.entity.Account;
import az.pashabank.ht.clients.model.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    AccountDTO toAccountDTO(Account account);

    List<AccountDTO> toAccountDTOs(List<Account> accounts);

    Account toAccount(AccountDTO accountDTO);
}
