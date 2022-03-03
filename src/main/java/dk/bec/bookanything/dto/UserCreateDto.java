package dk.bec.bookanything.dto;

import dk.bec.bookanything.service.RoleService;
import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    @Past
    private LocalDate birthDate;

    @Size(min = 9, max = 12)
    private String phoneNumber;

    @ForeignKeyExistsConstraint(serviceClass = RoleService.class)
    private Long roleId;

}
