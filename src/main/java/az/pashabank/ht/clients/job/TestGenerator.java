package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.model.ClientDTO;

import java.util.Random;

public class TestGenerator {
    private Random random = new Random();
    private static final String passwordSym = "ABCDEFJHIGKLMNOOPQRSTUVWXYZabcdefjehjklmnopqrstuvwxyz1234567890";

    public String login(String name) {
        StringBuilder stringBuilder = new StringBuilder(name);
        int x = random.nextInt(900) + 100;
        stringBuilder.append(".").append(x);
        return stringBuilder.toString().toLowerCase();
    }


    public static void main(String[] args) throws InterruptedException {

        TestGenerator testGenerator = new TestGenerator();
        ClientDTO clientDTO;

        RandomClientGenerator randomClientGenerator = new RandomClientGenerator();

        for (int i = 0; i < 20; i++) {
//            Thread.sleep(1000);
            clientDTO = randomClientGenerator.nameGenerator();
            String login = testGenerator.login(clientDTO.getName());
            System.out.println(login);
            System.out.println(clientDTO.toString());
        }

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        char[] s = passwordSym.toCharArray();
        for (int i = 0; i < 7; i++) {
            stringBuilder.append(s[random.nextInt(s.length)]);
        }
        System.out.println(stringBuilder);
    }
}
