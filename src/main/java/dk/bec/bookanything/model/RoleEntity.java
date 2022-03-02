package dk.bec.bookanything.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role")
public class RoleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String name;

    @OneToMany
    private List<UserEntity> userList;
}
