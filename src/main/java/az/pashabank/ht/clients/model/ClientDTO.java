package az.pashabank.ht.clients.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Component
public class ClientDTO {

    private long id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Size(min = 3)
    private String lastName;

    @Override
    public String toString() {
        return  "[ id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ']';
    }
}
