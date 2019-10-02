package az.pashabank.ht.clients.service.impl

import az.pashabank.ht.clients.entity.Account
import az.pashabank.ht.clients.entity.Client
import az.pashabank.ht.clients.model.AccountDTO
import az.pashabank.ht.clients.repository.AccountRepository
import az.pashabank.ht.clients.repository.ClientRepository
import spock.lang.Specification

class AccountServiceImplTest extends Specification {
    def accountRepository = [
            findAll          : {
                def list = new ArrayList<Account>()
                def account = new Account()
                account.setBalance(100.00)
                list.add(account)

                def account1 = new Account()
                account1.setBalance(200.00)
                list.add(account1)

                def account2 = new Account()
                account2.setBalance(300.00)
                list.add(account2)
                return list
            },

            findAllByClientId: { long id ->
                def list = new ArrayList<Account>()
                def account = new Account()
                account.setBalance(100.00)
                list.add(account)

                def account1 = new Account()
                account1.setBalance(200.00)
                list.add(account1)

                def account2 = new Account()
                account2.setBalance(300.00)
                list.add(account2)
                return list

            },
            save             : { Account account ->
                account.setId(1)
                return account
            },

            findById         : { long id ->
                def account = new Account()
                account.setId(1)
                account.setPassword("1234567")
                account.setBalance(100.00)
                return Optional.of(account)

            },

            deleteById : { long id ->
                return null
            }
    ] as AccountRepository

    def clientRepository = [
            findById: { long id ->
                def client = new Client()
                client.setId(1)
                return Optional.of(client)
            }
    ] as ClientRepository


    def accountService = new AccountServiceImpl(accountRepository, clientRepository)

    def "GetAllAccount"() {
        when:
        def listAccount = accountService.getAllAccount()

        then:
        listAccount[0].balance == 100.00
        listAccount[1].balance == 200.00
        listAccount[2].balance == 300.00
    }

    def "GetAccountByClientId"() {
        when:
        def listAccount = accountService.getAllAccount()

        then:
        listAccount[0].balance == 100.00
        listAccount[1].balance == 200.00
        listAccount[2].balance == 300.00


    }

    def "GetAccountById"() {
        when:
        def accountDTO = accountService.getAccountById(1)

        then:
        accountDTO.id == 1
        accountDTO.password == "1234567"
        accountDTO.balance == 100.00
    }

    def "AddAccount"() {
        setup:
        def accountDTO = new AccountDTO()
        accountDTO.setPassword("1234567")
        accountDTO.setClientId(1)

        when:
        def account = accountService.addAccount(accountDTO)

        then:
        account.id == 1
    }

    def "UpdateAccount"() {
        setup:
        def accountDTO = new AccountDTO()
        accountDTO.setId(1)
        accountDTO.setPassword("0000000")

        when:
        def accountUpdate = accountService.updateAccount(accountDTO)

        then:
        accountUpdate.password == "0000000"
        accountUpdate.balance == 100.00
    }

    def "DeleteAccount"() {
        when:
        def accountDTO = accountService.deleteAccount(1)

        then:
        accountDTO.password == "1234567"
        accountDTO.balance == 100.00

    }

    def "UpdateBalance"() {
        setup:
        def accountDTO = new AccountDTO()
        accountDTO.setId(1)
        accountDTO.setPassword("1234567")
        accountDTO.setBalance(-50.00)

        when:
        def accountDtoDb = accountService.updateBalance(accountDTO)

        then:

        accountDtoDb.balance == 50.00
    }
}
