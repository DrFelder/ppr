/*
 * Project Puerto Rico
 * Copyright (C) 2018  Thomas Pötzsch, Stephan Stroh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package is.surreal.ppr.repository;

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
