package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.model.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomAccountGenerate {
    private static final String passworSym = "ABCDEFJHIGKLMNOOPQRSTUVWXYZabcdefjehjklmnopqrstuvwxyz1234567890";

    private static Random random = new Random();

//    private String login(String name) {
//        stringBuilder = new StringBuilder(name);
//        int x = random.nextInt(900) + 100;
//        stringBuilder.append(".").append(x);
//        return stringBuilder.toString().toLowerCase();
//    }

    private String password() {
        StringBuilder stringBuilder = new StringBuilder();

        char[] s = passworSym.toCharArray();
        for (int i = 0; i < 7; i++) {
            stringBuilder.append(s[random.nextInt(s.length)]);
        }
        return stringBuilder.toString();
    }

    public AccountDTO accountGenerate(String name) {
        AccountDTO accountDTO = new AccountDTO();
//        accountDTO.setLogin(login(name));
        accountDTO.setPassword(password());
        return accountDTO;
    }
}

