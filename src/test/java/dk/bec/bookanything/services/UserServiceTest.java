package dk.bec.bookanything.services;

import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.repository.UserRepository;
import dk.bec.bookanything.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    UserService userService;

    UserRepository userRepository;


    private final List<UserEntity> userList = new ArrayList<>();
    private final UserEntity userEntity1 = UserEntity.builder()
            .email("sasha@gmail.com")
            .password("123456789")
            .birthDate(LocalDate.of(2000, 12, 22))
            .phoneNumber("99988877")
            .role(RoleEntity.builder().name("User").build()).build();
    private final UserEntity userEntity2 = UserEntity.builder()
            .email("masha@gmail.com")
            .password("123456789")
            .birthDate(LocalDate.of(2005, 6, 8))
            .phoneNumber("678429851")
            .role(RoleEntity.builder().name("User").build()).build();
    private final UserEntity userEntity3 = UserEntity.builder()
            .email("gunia@gmail.com")
            .password("123456789")
            .birthDate(LocalDate.of(1990, 2, 15))
            .phoneNumber("900655432")
            .role(RoleEntity.builder().name("User").build()).build();

    @Before
    public void setup() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void getAllUsersTest() {

        userList.add(userEntity1);
        userList.add(userEntity2);
        userList.add(userEntity3);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<UserEntity> userEntityList = userService.getUsers();
        assertEquals(3, userEntityList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUserByIdTest() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity1));
        Optional<UserEntity> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals("sasha@gmail.com", result.get().getEmail());
        assertEquals("99988877", result.get().getPhoneNumber());
        assertEquals(LocalDate.of(2000, 12, 22), result.get().getBirthDate());
    }



    @Test
    public void addUserTest() {
        Mockito.when(userRepository.save(userEntity1)).thenReturn(userEntity1);
        UserEntity result = userService.addUser(userEntity1);
        assertEquals("sasha@gmail.com", result.getEmail());
        assertEquals("99988877", result.getPhoneNumber());
        assertEquals(LocalDate.of(2000, 12, 22), result.getBirthDate());

    }

    @Test
    public void deleteUserTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity1));
        doNothing().when(userRepository).delete(userEntity1);
        userService.deleteUserById(1L);
        verify(userRepository, times(1)).delete(userEntity1);
    }

    @Test
    public void updateUserTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity1));
        userService.updateUser(1L, userEntity2);
        verify(userRepository, times(1)).save(userEntity2);

    }


}

