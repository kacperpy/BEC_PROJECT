package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LocalDateType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String email;

    private String password;

    private LocalDateType birthDate;

    private String phoneNumber;

    private Long roleId;

}
