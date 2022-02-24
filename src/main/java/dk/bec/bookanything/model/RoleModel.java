package dk.bec.bookanything.model;


import lombok.Data;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name = "role")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(length = 200)
    private String name;

    @OneToMany
    private List<UserModel> userList;
}
