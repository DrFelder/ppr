package is.surreal.ppr.repository;

import is.surreal.ppr.model.Notificationread;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "read", path = "read")
public interface ReadRepository extends PagingAndSortingRepository<Notificationread, Long> {

}
