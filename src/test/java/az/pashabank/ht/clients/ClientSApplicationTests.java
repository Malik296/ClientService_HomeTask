package az.pashabank.ht.clients;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientSApplicationTests {

    @Test(timeout = 1000)
    public void contextLoads() {
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
    }

}
