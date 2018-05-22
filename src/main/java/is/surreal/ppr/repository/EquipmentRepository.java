package is.surreal.ppr.repository;

import is.surreal.ppr.model.Equipment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "equipment", path = "equipment")
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long> {

}
