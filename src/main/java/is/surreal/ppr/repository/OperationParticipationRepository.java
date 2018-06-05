package is.surreal.ppr.repository;

import is.surreal.ppr.model.Operationparticipation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.ColumnResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
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
