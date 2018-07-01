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

import is.surreal.ppr.model.Operationparticipation;
import is.surreal.ppr.repository.HelperRepository;
import is.surreal.ppr.repository.OperationRepository;
import is.surreal.ppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
                if (operationparticipation.getEquipmentByEquipmentId().getOperationId() == operationId && operationparticipation.getAccepted() == 1)
                    return "PARTICIPATOR";
            } else {
                if (operationparticipation.getHelperByHelperId().getOperationId() == operationId && operationparticipation.getAccepted() == 1)
                    return "PARTICIPATOR";
            }
        }
        return "WATCHER";
    }

}
