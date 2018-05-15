/*
 * Project Puerto Rico
 * Copyright (C) 2017  Thomas Pötzsch, Stephan Stroh
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

import is.surreal.ppr.model.Equipment;
import is.surreal.ppr.model.Helper;
import is.surreal.ppr.model.Operation;
import is.surreal.ppr.model.User;
import is.surreal.ppr.repository.EquipmentRepository;
import is.surreal.ppr.repository.HelperRepository;
import is.surreal.ppr.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class DetailController {

    @Autowired
    private OperationRepository operationRepository;

    @RequestMapping(value = "/detail")
    public ModelAndView detail(ModelAndView model, @RequestParam Long id) throws IOException {
        Optional<Operation> optOperation = operationRepository.findById(id);
        Operation operation = optOperation.get();
        model.addObject("operation", operation);
        model.setViewName("detail");

        return model;
    }
}
