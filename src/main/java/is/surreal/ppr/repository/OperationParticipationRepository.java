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

import is.surreal.ppr.model.Operationparticipation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "helper", path = "helper")
public interface OperationParticipationRepository extends PagingAndSortingRepository<Operationparticipation, Long> {

    @Query(value = "select user_id, helper_id, accepted, declined, question, answer from Operationparticipation " +
            "inner join Helper H on Operationparticipation.helper_id = H.id " +
            "where user_id = :userId and operation_id = :operationId", nativeQuery = true)
    List<Object[]> findHelperParticipation(@Param("userId") Long userId, @Param("operationId") Long operationId);

    @Query(value = "select user_id, equipment_id, accepted, declined, question, answer from Operationparticipation " +
            "inner join Equipment E on Operationparticipation.equipment_id = E.id " +
            "where user_id = :userId and operation_id = :operationId", nativeQuery = true)
    List<Object[]> findEquipmentParticipation(@Param("userId") Long userId, @Param("operationId") Long operationId);

}
