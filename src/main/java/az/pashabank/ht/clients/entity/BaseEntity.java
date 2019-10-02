package az.pashabank.ht.clients.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
abstract class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name="update_date")
    private LocalDateTime updateDate;
}
