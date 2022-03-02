package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String email;

    private String password;

    private LocalDateTime birthDate;

    private String phoneNumber;

    private Long roleId;

}
