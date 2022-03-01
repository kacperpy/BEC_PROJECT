package dk.bec.bookanything.service;


import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> getRole(String uuid) {
        return  Optional.of(roleRepository.findRoleEntityByUuid(UUID.fromString(uuid)));
    }

    public Optional<RoleEntity> createRole(RoleEntity roleEntity) {
        return Optional.of(roleRepository.save(roleEntity));
    }


    public void deleteRole(String uuid) {
        roleRepository.deleteRoleEntityByUuid(UUID.fromString(uuid));
    }

    public Optional<RoleEntity> updateRole(RoleEntity roleEntity) {
        RoleEntity roleFromDB = roleRepository.findRoleEntityByUuid(roleEntity.getUuid());
        roleFromDB.setName(roleEntity.getName());
        roleFromDB.setUserList(roleEntity.getUserList());
        return Optional.of(roleRepository.save(roleFromDB));

    }
}
