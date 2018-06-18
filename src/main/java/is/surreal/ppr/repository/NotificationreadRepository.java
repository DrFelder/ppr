package is.surreal.ppr.repository;

import is.surreal.ppr.model.Equipment;
import is.surreal.ppr.model.Notificationread;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notificationread", path = "notificationread")
public interface NotificationreadRepository extends PagingAndSortingRepository<Notificationread, Long> {

}
