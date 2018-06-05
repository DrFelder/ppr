package is.surreal.ppr.controller;

import is.surreal.ppr.model.Operation;
import is.surreal.ppr.model.Operationparticipation;
import is.surreal.ppr.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/operationparticipation")
public class OperationParticipationController {

    private UserRepository userRepository;
    private OperationParticipationRepository operationParticipationRepository;

    @Autowired
    public OperationParticipationController(
            OperationRepository operationRepository, UserRepository userRepository,
            OperationParticipationRepository operationParticipationRepository,
            HelperRepository helperRepository, EquipmentRepository equipmentRepository
    ) {
        this.userRepository = userRepository;
        this.operationParticipationRepository = operationParticipationRepository;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    ResponseEntity<?> addOperationParticipation(
            @Valid @RequestBody Operationparticipation operationparticipation
    ) throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        operationparticipation.setUserId(this.userRepository.findByUsername(auth.getName()).getId());
        Operationparticipation result = operationParticipationRepository.save(operationparticipation);
        URI location = new URI("http://localhost:8081/#/operationparticipation/" + result.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/helper/{operationId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    List<Object[]> getHelperParticipationById(@PathVariable Long operationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.operationParticipationRepository.findHelperParticipation(
                this.userRepository.findByUsername(auth.getName()).getId(),
                operationId
        );
    }

    @GetMapping("/equipment/{operationId}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    List<Object[]> getEquipmentParticipationById(@PathVariable Long operationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.operationParticipationRepository.findEquipmentParticipation(
                this.userRepository.findByUsername(auth.getName()).getId(),
                operationId
        );
    }

}
