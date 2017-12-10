package is.surreal.ppr.controller;

import is.surreal.ppr.model.Operation;
import is.surreal.ppr.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationController {

    @Autowired
    OperationRepository operationRepository;

    @PostMapping("/applyforoperation")
    public String applyForRequirement(@ModelAttribute Operation operation, RedirectAttributes redirectAttributes) {
        operation.setOrganizerId(101);
        redirectAttributes.addAttribute("id", operation.getId());
        return "redirect:/detail";
    }

}
