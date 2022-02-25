package dk.bec.bookanything.model;

import org.hibernate.type.LocalDateType;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private LocalDateType birthDate;

    @Column
    private String phoneNumber;

    @ManyToOne
    private RoleModel role;

    @OneToMany
    private List<ReservationModel> reservations;
}
