package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.BookableObjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookableObjectRepository extends CrudRepository<BookableObjectEntity, Long> {

}
