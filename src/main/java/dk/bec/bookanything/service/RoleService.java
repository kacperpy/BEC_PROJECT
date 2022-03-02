package dk.bec.bookanything.service;


import dk.bec.bookanything.model.RoleEntity;
import dk.bec.bookanything.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> getRole(Long id) {
        return Optional.of(roleRepository.findRoleEntityById(id));
    }

    public Optional<RoleEntity> createRole(RoleEntity roleEntity) {
        return Optional.of(roleRepository.save(roleEntity));
    }


    public void deleteRole(Long id) {
        roleRepository.deleteRoleEntityById(id);
    }

    public Optional<RoleEntity> updateRole(RoleEntity roleEntity) {
        RoleEntity roleFromDB = roleRepository.findRoleEntityById(roleEntity.getId());
        roleFromDB.setName(roleEntity.getName());
        roleFromDB.setUserList(roleEntity.getUserList());
        return Optional.of(roleRepository.save(roleFromDB));

    }
}
