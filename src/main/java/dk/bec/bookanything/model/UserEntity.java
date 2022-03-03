package dk.bec.bookanything.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private LocalDate birthDate;

    @Column
    private String phoneNumber;

    @ManyToOne
    private RoleEntity role;

    @OneToMany
    private List<ReservationEntity> reservations;


}
