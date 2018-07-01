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

import is.surreal.ppr.strategy.AdminStyle;
import is.surreal.ppr.strategy.ChangingStyle;
import is.surreal.ppr.strategy.NormalUserStyle;
import is.surreal.ppr.strategy.OperationOwnerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/style")
public class StyleController {

    @Autowired
    public StyleController() {
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    String getStyle(@RequestParam("role") String role) throws URISyntaxException {

        ChangingStyle changeStyle = new ChangingStyle(new NormalUserStyle());
        if (role.equals("ADMIN")) {
            changeStyle.changeStrategy(new AdminStyle());
        } else if (role.equals("ORGANIZER")) {
            changeStyle.changeStrategy(new OperationOwnerStyle());
        }
        return changeStyle.applyStyle();
    }

}
