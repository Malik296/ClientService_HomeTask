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

    private int id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return  "[ id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ']';
    }
}
