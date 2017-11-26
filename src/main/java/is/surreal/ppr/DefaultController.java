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

package is.surreal.ppr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping(value = "/")
    public ModelAndView homepage(ModelAndView model) {
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/index.html")
    public ModelAndView indexHtml(ModelAndView model) {
        model.setViewName("index");

        return model;
    }

}
