package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityById(Long id);

    RoleEntity findByName(String name);

    void deleteRoleEntityById(Long id);
}
