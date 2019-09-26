package az.pashabank.ht.clients.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Client extends BaseEntity {
    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private long id;

    @Column
    private String name;

    @Column
    private String lastName;
}
