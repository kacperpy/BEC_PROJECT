package dk.bec.bookanything.services;


import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.repository.RoleRepository;
import dk.bec.bookanything.service.RoleService;
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
public class RoleServiceTest {

    RoleService roleService;
    RoleRepository roleRepository;

    private final List<RoleEntity> roleList = new ArrayList<>();
    private final RoleEntity roleEntity1 = RoleEntity.builder()
            .id(1L)
            .name("Regular user")
            .userList(new ArrayList<>()).build();
    private final RoleEntity roleEntity2 = RoleEntity.builder()
            .id(1L)
            .name("Premium user")
            .userList(new ArrayList<>()).build();
    private final RoleEntity roleEntity3 = RoleEntity.builder()
            .id(1L)
            .name("Admin")
            .userList(new ArrayList<>()).build();



    @Before
    public void setup() {
        roleRepository = mock(RoleRepository.class);
        roleService = new RoleService(roleRepository);
    }


    @Test
    public void getAllRolesTest(){
        roleList.add(roleEntity1);
        roleList.add(roleEntity2);
        roleList.add(roleEntity3);

        Mockito.when(roleRepository.findAll()).thenReturn(roleList);
        List<RoleEntity> result = roleService.getRoles();
        assertEquals(3, result.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    public void getRoleByIdTest(){

        Mockito.when(roleRepository.findRoleEntityById(1L)).thenReturn(roleEntity1);
        Optional<RoleEntity> result = roleService.getRole(1L);
        assertTrue(result.isPresent());
        assertEquals("Regular user", result.get().getName());

    }

    @Test
    public void addRoleTest(){
        Mockito.when(roleRepository.save(roleEntity1)).thenReturn(roleEntity1);
        Optional<RoleEntity> result = roleService.createRole(roleEntity1);
        assertTrue(result.isPresent());
        assertEquals("Regular user", result.get().getName());
        verify(roleRepository, times(1)).save(roleEntity1);
    }

    @Test
    public void deleteRoleTest(){

        doNothing().when(roleRepository).deleteRoleEntityById(1L);
        roleService.deleteRole(1L);
        verify(roleRepository, times(1)).deleteRoleEntityById(1L);
    }



}
