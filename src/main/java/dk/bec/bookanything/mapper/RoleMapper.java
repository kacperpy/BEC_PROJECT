package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.RoleCreateDto;
import dk.bec.bookanything.dto.RoleReadDto;
import dk.bec.bookanything.dto.UserReadDto;
import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    private final UserService userService;
    private final UserMapper userMapper;

    public RoleMapper(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public RoleEntity roleDtoToEntity(RoleCreateDto roleCreateDto) {
        List<UserEntity> userList = new ArrayList<>();
        for (Long id : roleCreateDto.getUserIdList()) {
            Optional<UserEntity> userEntity = userService.getUsers().stream().filter(e -> e.getId().equals(id)).findFirst();
            userEntity.ifPresent(userList::add);
        }
        return RoleEntity.builder()
                .name(roleCreateDto.getName())
                .userList(userList).build();

    }

    public RoleReadDto roleEntityToDto(RoleEntity roleEntity) {
        List<UserReadDto> userReadDtoList = roleEntity.getUserList().stream().map(userMapper::mapUserEntityToUserReadDto).collect(Collectors.toList());
        return RoleReadDto.builder()
                .name(roleEntity.getName())
                .userList(userReadDtoList).build();

    }

    public RoleEntity roleDtoToEntityWhenModified(RoleCreateDto roleCreateDto, Long id) {
        List<UserEntity> userList = new ArrayList<>();
        for (Long i : roleCreateDto.getUserIdList()) {
            Optional<UserEntity> userEntity = userService.getUsers().stream().filter(e -> e.getId().equals(i)).findFirst();
            userEntity.ifPresent(userList::add);
        }
        return RoleEntity.builder()
                .id(id)
                .name(roleCreateDto.getName())
                .userList(userList).build();

    }
}
