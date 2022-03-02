package dk.bec.bookanything.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Data
public class RoleCreateDto {

    @NotBlank(message = "Name is mandatory field!")
    private String name;

    private List<Long> userIdList;
}
