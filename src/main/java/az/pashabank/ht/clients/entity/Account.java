package az.pashabank.ht.clients.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "account")
@Getter
@Setter
public class Account extends BaseEntity {

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
}
