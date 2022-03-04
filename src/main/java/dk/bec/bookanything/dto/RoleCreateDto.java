package dk.bec.bookanything.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto {

    @NotBlank(message = "Name is mandatory field!")
    private String name;

    private List<Long> userIdList;
}
