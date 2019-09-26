package az.pashabank.ht.clients.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
abstract class BaseEntity {
    @Column
    private LocalDateTime createData;

    @Column
    private LocalDateTime updateData;
}
