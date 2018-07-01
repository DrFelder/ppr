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

import is.surreal.ppr.model.Notification;
import is.surreal.ppr.model.Operation;
import is.surreal.ppr.repository.NotificationRepository;
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
    private NotificationRepository notificationRepository;

    @Autowired
    public OperationController(OperationRepository operationRepository, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
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
        if (result.getStartDate() != null) {
            Notification notification = new Notification();
            notification.setOperationId(result.getId());
            notification.setMessage("");
            this.notificationRepository.save(notification);
        }
        URI location = new URI("http://localhost:8081/#/operation/" + result.getId());
        return ResponseEntity.created(location).build();
    }
}
