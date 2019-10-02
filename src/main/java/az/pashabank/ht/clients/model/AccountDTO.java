package az.pashabank.ht.clients.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Component
public class AccountDTO {
    private long id;

    @Size(min = 5, message = "Password Length min 5")
    private String password;

    private long clientId;

    private BigDecimal balance;
}
