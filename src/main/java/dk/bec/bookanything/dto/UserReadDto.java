package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDto {

    private String email;

    private String password;

    private LocalDateTime birthDate;

    private String phoneNumber;

    private RoleEntity role;

    private List<ReservationEntity> reservations;
}
