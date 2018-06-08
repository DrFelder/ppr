package is.surreal.ppr.controller;

import is.surreal.ppr.model.Equipment;
import is.surreal.ppr.model.Helper;
import is.surreal.ppr.model.Operationparticipation;
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
@RequestMapping("/rest/currentprivileges")
public class GetCurrentPrivilegesController {

    private HelperRepository helperRepository;
    private OperationRepository operationRepository;
    private UserRepository userRepository;

    @Autowired
    public GetCurrentPrivilegesController(HelperRepository helperRepository, OperationRepository operationRepository, UserRepository userRepository) {
        this.helperRepository = helperRepository;
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{operationId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    String getCurrentPrivileges(@PathVariable Long operationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = this.userRepository.findByUsername(auth.getName()).getId();
        Long operationOrganizerId = this.operationRepository.findById(operationId).get().getOrganizerId();
        if (auth.getAuthorities().contains("ADMIN_USER")) return "ADMIN";
        if (userId == operationOrganizerId) return "ORGANIZER";
        for (Operationparticipation operationparticipation : this.userRepository.findById(userId).get().getOperationparticipationsById()) {
            if (operationparticipation.getEquipmentId() != null) {
                if (operationparticipation.getEquipmentByEquipmentId().getOperationId() == operationId) return "PARTICIPATOR";
            } else {
                if (operationparticipation.getHelperByHelperId().getOperationId() == operationId) return "PARTICIPATOR";
            }
        }
        return "WATCHER";
    }

}
