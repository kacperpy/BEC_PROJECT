package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.BookableObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookableObjectRepository extends JpaRepository<BookableObjectEntity, Long> {

}