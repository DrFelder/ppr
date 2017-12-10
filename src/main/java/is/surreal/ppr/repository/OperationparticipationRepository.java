package is.surreal.ppr.repository;

import is.surreal.ppr.model.Operationparticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationparticipationRepository extends JpaRepository<Operationparticipation, Long> {

}
