package is.surreal.ppr.controller;

import is.surreal.ppr.model.Operation;
import is.surreal.ppr.repository.OperationRepository;
import is.surreal.ppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/operation")
public class OperationController {

    private OperationRepository operationRepository;
    private UserRepository userRepository;

    @Autowired
    public OperationController(OperationRepository operationRepository, UserRepository userRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    Iterable<Operation> getOperations() {
        return this.operationRepository.findAll();
    }

    @GetMapping("/{operationId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    Operation getOperationById(@PathVariable Long operationId) {
        if (this.operationRepository.findById(operationId).isPresent()) {
            return this.operationRepository.findById(operationId).get();
        } else {
            return null;
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    ResponseEntity<?> addOperation(@Valid @RequestBody Operation input) throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        input.setOrganizerId(this.userRepository.findByUsername(auth.getName()).getId());
        Operation result = this.operationRepository.save(input);
        URI location = new URI("http://localhost:8081/#/operation/" + result.getId());
        return ResponseEntity.created(location).build();
    }

}