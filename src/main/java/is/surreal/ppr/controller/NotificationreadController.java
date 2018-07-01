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

import is.surreal.ppr.model.Notificationread;
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
import java.sql.Timestamp;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/read")
public class NotificationreadController {

    private NotificationRepository notificationRepository;
    private OperationRepository operationRepository;
    private UserRepository userRepository;
    private OperationParticipationRepository operationParticipationRepository;
    private ReadRepository readRepository;
    private NotificationreadRepository notificationreadRepository;


    @Autowired
    public NotificationreadController(NotificationreadRepository notificationreadRepository, ReadRepository readRepository, OperationRepository operationRepository, NotificationRepository notificationRepository, UserRepository userRepository, OperationParticipationRepository operationParticipationRepository) {
        this.notificationRepository = notificationRepository;
        this.operationRepository = operationRepository;
        this.operationParticipationRepository = operationParticipationRepository;
        this.userRepository = userRepository;
        this.readRepository = readRepository;
        this.notificationreadRepository = notificationreadRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    Iterable<Notificationread> getReads() {
        return this.readRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    ResponseEntity<?> addRead(@Valid @RequestBody Notificationread notificationread) throws URISyntaxException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        notificationread.setUserId(this.userRepository.findByUsername(auth.getName()).getId());
        notificationread.setDate(new Timestamp(System.currentTimeMillis()));
        Notificationread result =  this.notificationreadRepository.save(notificationread);
        URI location = new URI("http://localhost:8081/#/rest/read" + result.getId());
        return ResponseEntity.created(location).build();
    }
}
