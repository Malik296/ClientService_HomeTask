package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.model.ClientDTO;

public class TestGenerator {
    public static void main(String[] args) throws InterruptedException {
        RandomClientGenerator randomClientGenerator = new RandomClientGenerator();

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            ClientDTO clientDTO = randomClientGenerator.nameGenerator();
            System.out.println(clientDTO.toString());
        }
    }
}