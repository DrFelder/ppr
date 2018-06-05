package unit;

import is.surreal.ppr.Application;
import is.surreal.ppr.model.Operation;
import is.surreal.ppr.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class CreateOperationTest {

    @Autowired
    OperationRepository operationRepository;

    @Test
    void operationRepositoryTest() {
        Operation operation = new Operation();
        operation.setDate(new Date(1370145732000L));
        operation.setLocation("This is a testing location");

        operationRepository.save(operation);

        Optional<Operation> optOperation = operationRepository.findById(102L);
        Operation savedOperation = optOperation.get();
        assertEquals(new Date(1370145732000L), savedOperation.getDate());

        Optional<Operation> optSecondOperation = operationRepository.findById(102L);
        Operation secondOperation = optSecondOperation.get();
        assertEquals(operation, secondOperation);

        Optional<Operation> optThirdOperation = operationRepository.findById(123L);
        assertFalse(optThirdOperation.isPresent());
    }

}
