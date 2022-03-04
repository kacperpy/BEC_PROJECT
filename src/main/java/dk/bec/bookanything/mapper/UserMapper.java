package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.UserCreateDto;
import dk.bec.bookanything.dto.UserReadDto;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserEntity mapUserCreateDtoToUserEntity(UserCreateDto userCreateDto, Long id) {

        return UserEntity.builder()
                .id(id)
                .birthDate(userCreateDto.getBirthDate())
                .email(userCreateDto.getEmail())
                .phoneNumber(userCreateDto.getPhoneNumber())
                .role(roleService.getRole(userCreateDto.getRoleId()).orElse(null))
                .password(userCreateDto.getPassword()).build();
    }

    public UserReadDto mapUserEntityToUserReadDto(UserEntity userEntity) {
        return UserReadDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .birthDate(userEntity.getBirthDate())
                .phoneNumber(userEntity.getPhoneNumber())
                .role(roleService.getRole(userEntity.getId()).orElse(null)).build();

    }
}
