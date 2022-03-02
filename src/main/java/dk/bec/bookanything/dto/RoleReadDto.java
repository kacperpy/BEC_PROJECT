package dk.bec.bookanything.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RoleReadDto {

    private Long id;

    private String name;

    private List<UserReadDto> userList;
}
