package is.surreal.ppr.controller;

import is.surreal.ppr.model.User;
import is.surreal.ppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{username}")
    User getUserByName(@PathVariable String username) {
        return this.userRepository.findByUsername(username);
    }

    @GetMapping("/current")
    User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.findByUsername(auth.getName());
    }

}
