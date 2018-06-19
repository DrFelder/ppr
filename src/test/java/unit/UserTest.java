package unit;

import is.surreal.ppr.Application;
import is.surreal.ppr.model.User;
import is.surreal.ppr.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void generalUserTest() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("username-test");
        user.setPassword("Wow!");

        this.userRepository.save(user);

        User savedUser = this.userRepository.findByUsername("username-test");
        assertEquals(savedUser.getEmail(), user.getEmail());
        assertEquals(savedUser.getUsername(), user.getUsername());
        assertEquals(savedUser.getPassword(), user.getPassword());
        assertNotNull(savedUser.getId());
    }

}
