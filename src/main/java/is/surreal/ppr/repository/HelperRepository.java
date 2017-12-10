package is.surreal.ppr.repository;

import is.surreal.ppr.model.Helper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRepository extends JpaRepository<Helper, Long> {

}
