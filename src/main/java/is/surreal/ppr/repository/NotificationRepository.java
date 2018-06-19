package is.surreal.ppr.repository;

import is.surreal.ppr.model.Helper;
import is.surreal.ppr.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "notification", path = "notification")
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

    @Query(value = "select * from" +
            "  (select" +
            "     Notification.id," +
            "     Notification.operation_id," +
            "     message" +
            "   from Notification" +
            "     inner join Operation O on Notification.operation_id = O.id" +
            "     inner join Equipment E on O.id = E.operation_id" +
            "     left join Operationparticipation O2 on E.id = O2.equipment_id" +
            "   where O2.accepted = true and O2.user_id = :userId" +
            "  ) as NOEO2ioimeiaui" +
            "union all (" +
            "  select" +
            "    Notification.id," +
            "    Notification.operation_id," +
            "    message" +
            "  from Notification" +
            "    inner join Operation O on Notification.operation_id = O.id" +
            "    inner join Helper H on O.id = H.operation_id" +
            "    left join Operationparticipation O2 on H.id = O2.helper_id" +
            "  where O2.accepted = true and O2.user_id = :userId);", nativeQuery = true)
    List<Object> findNotificationForUser(@Param("userId") Long userId);

}
