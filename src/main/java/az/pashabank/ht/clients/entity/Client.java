package az.pashabank.ht.clients.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "client")
@EqualsAndHashCode(callSuper = false)
public class Client extends BaseEntity {

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;
}


