package az.pashabank.ht.clients.mapper

import az.pashabank.ht.clients.model.AccountDTO
import spock.lang.Specification

class AccountMapperTest extends Specification {

    void setup() {
    }

    void mapperTest() {
        setup:
        def accountDTO = new AccountDTO()
        accountDTO.setId(1)
        accountDTO.setPassword("1234567")
        accountDTO.setClientId(1)
        accountDTO.setBalance(100.00)

        when:
        def account = AccountMapper.INSTANCE.toAccount(accountDTO)

        then:
        accountDTO.id == account.id
        accountDTO.password == account.password
        accountDTO.balance == account.balance
    }
}
