package az.pashabank.ht.clients.job;

import az.pashabank.ht.clients.model.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomClientGenerator {
    private static Random random = new Random();

    private static final String[] MAN_NAME = {"Əli", "Elçin", "Vüqar", "Anar", "Elnur", "Samir", "Elşən", "Rəşad", "İlqar", "Vüsal", "Məmməd", "Məhəmməd"};
    private static final String[] MAN_LAST_NAME = {"Məmmədov", "Əliyev", "Hüseynov", "İsmayılov", "Abdullayev", "Məhərrəmli", "Nərimanlı"};

    private static final String[] WOMAN_NAME = {"Sevinc", "Günel", "Leyla", "Aygün", "Günay", "Sevda", "Vüsalə", "Könül", "Təranə", "Samirə"};
    private static final String[] WOMAN_LAST_NAME = {"Məmmədova", "Əliyeva", "Hüseynova", "İsmayılova", "Abdullayeva", "Məhərrəmova", "Əhmədova"};


    private ClientDTO clientDTO;

    public ClientDTO nameGenerator() {
        clientDTO = new ClientDTO();
        if (random.nextBoolean() == true) {
            return manNameGenerator();
        } else {
            return womanNameGenerator();
        }
    }

    private ClientDTO womanNameGenerator() {
        int n = random.nextInt(WOMAN_NAME.length);
        int ln = random.nextInt(WOMAN_LAST_NAME.length);
        clientDTO.setName(WOMAN_NAME[n]);
        clientDTO.setLastName(WOMAN_LAST_NAME[ln]);
        return clientDTO;
    }

    private ClientDTO manNameGenerator() {
        int n = random.nextInt(MAN_NAME.length);
        int ln = random.nextInt(MAN_LAST_NAME.length);
        clientDTO.setName(MAN_NAME[n]);
        clientDTO.setLastName(MAN_LAST_NAME[ln]);
        return clientDTO;
    }

}