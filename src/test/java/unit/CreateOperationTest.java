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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@TestPropertySource(locations = "classpath:testing.properties")
public class CreateOperationTest {

    @Autowired
    OperationRepository operationRepository;

    @Test
    void operationRepositoryTest() {
        Operation operation = new Operation();
        operation.setDate(new Date(1982, 6, 5));
        operation.setLocation("This is a testing location");

        operationRepository.save(operation);

        Optional<Operation> optOperation = operationRepository.findById(1L);
        Operation savedOperation = optOperation.get();
        assertEquals(new Date(1982, 6, 5), savedOperation.getDate());

        Optional<Operation> optSecondOperation = operationRepository.findById(1L);
        Operation secondOperation = optSecondOperation.get();
        assertEquals(operation, secondOperation);

        Optional<Operation> optThirdOperation = operationRepository.findById(123L);
        assertFalse(optThirdOperation.isPresent());
    }

}
