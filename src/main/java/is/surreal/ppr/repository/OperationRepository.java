package is.surreal.ppr.repository;

import is.surreal.ppr.model.Operation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "operation", path = "operation")
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {

}
