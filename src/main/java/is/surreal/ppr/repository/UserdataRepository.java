package is.surreal.ppr.repository;

import is.surreal.ppr.model.Userdata;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// TODO: Für alles CRUD-Repo nehmen?
@RepositoryRestResource(collectionResourceRel = "userdetails", path = "userdetails")
public interface UserdataRepository extends PagingAndSortingRepository<Userdata, Long> {

}