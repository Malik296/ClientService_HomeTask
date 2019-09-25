package az.pashabank.ht.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClientSApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientSApplication.class, args);
    }
}
