package is.surreal.ppr.repository;

import is.surreal.ppr.model.Helper;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "helper", path = "helper")
public interface HelperRepository extends PagingAndSortingRepository<Helper, Long> {

}
