package dk.bec.bookanything.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.bec.bookanything.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
