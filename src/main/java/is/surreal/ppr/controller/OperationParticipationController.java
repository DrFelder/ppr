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
