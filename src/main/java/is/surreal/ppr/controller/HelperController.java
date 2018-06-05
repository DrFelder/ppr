package is.surreal.ppr.controller;

import is.surreal.ppr.model.Equipment;
import is.surreal.ppr.model.Helper;
import is.surreal.ppr.repository.HelperRepository;
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
@RequestMapping("/rest/helper")
public class HelperController {

    private HelperRepository helperRepository;
    private OperationRepository operationRepository;
    private UserRepository userRepository;

    @Autowired
    public HelperController(HelperRepository helperRepository, OperationRepository operationRepository, UserRepository userRepository) {
        this.helperRepository = helperRepository;
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    ResponseEntity<?> addEquipment(@Valid @RequestBody Helper input) throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = this.userRepository.findByUsername(auth.getName()).getId();
        Long operationOrganizerId = this.operationRepository.findById(input.getOperationId()).get().getOrganizerId();
        if (userId == operationOrganizerId) {
            Helper result = this.helperRepository.save(input);
            URI location = new URI("http://localhost:8081/#/operation/" + result.getId());
            return ResponseEntity.created(location).build();
        }
        return null;
    }

}