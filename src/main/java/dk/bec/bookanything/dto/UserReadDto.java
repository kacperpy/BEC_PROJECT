package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {

    private Long id;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String phoneNumber;

    private RoleEntity role;

    private List<ReservationEntity> reservations;
}
