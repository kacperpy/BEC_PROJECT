package dk.bec.bookanything.services;

import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.repository.UserRepository;
import dk.bec.bookanything.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    List<UserEntity> userList = new ArrayList<UserEntity>();
    UserEntity userEntity1 = UserEntity.builder()
            .email("sasha@gmail.com")
            .password("123456789")
            .birthDate( LocalDateTime.of(2000,12, 22, 12,00))
            .phoneNumber("99988877")
            .role(RoleEntity.builder().name("User").build()).build();
    UserEntity userEntity2 = UserEntity.builder()
            .email("masha@gmail.com")
            .password("123456789")
            .birthDate( LocalDateTime.of(2000,12, 22, 12,00))
            .phoneNumber("99988877")
            .role(RoleEntity.builder().name("User").build()).build();
    UserEntity userEntity3 = UserEntity.builder()
            .email("gunia@gmail.com")
            .password("123456789")
            .birthDate( LocalDateTime.of(2000,12, 22, 12,00))
            .phoneNumber("99988877")
            .role(RoleEntity.builder().name("User").build()).build();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest(){
        userList.add(userEntity1);
        userList.add(userEntity2);
        userList.add(userEntity3);

        when(userRepository.findAll()).thenReturn(userList);

        //test
        List<UserEntity> userEntityList = userService.getUsers();
        assertEquals(3, userEntityList.size());

    }

}

