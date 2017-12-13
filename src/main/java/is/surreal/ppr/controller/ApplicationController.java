package is.surreal.ppr.controller;

import is.surreal.ppr.model.Operation;
import is.surreal.ppr.model.Operationparticipation;
import is.surreal.ppr.repository.OperationparticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationController {

    @Autowired
    OperationparticipationRepository operationparticipationRepository;

    @GetMapping("/applyforhelper")
    public String getHelperApplicationForm(Model model, @RequestParam int userId, @RequestParam int helperId, @RequestParam int operationId) {
        Operationparticipation operationparticipation = new Operationparticipation();
        operationparticipation.setUserId(userId);
        operationparticipation.setHelperId(helperId);
        operationparticipation.setEquipmentId(operationId);
        model.addAttribute("operationparticipation", operationparticipation);
        return "applyforhelper";
    }

    @PostMapping("/applyforhelper")
    public String submitHelperApplicationForm(@ModelAttribute Operationparticipation operationparticipation, @ModelAttribute Operation operation, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", operationparticipation.getEquipmentId());
        operationparticipation.setEquipmentId(null);
        operationparticipationRepository.save(operationparticipation);
        return "redirect:/detail";
    }

    @GetMapping("/applyforequipment")
    public String getEquipmentApplicationForm(Model model, @RequestParam int userId, @RequestParam int equipmentId, @RequestParam int operationId) {
        Operationparticipation operationparticipation = new Operationparticipation();
        operationparticipation.setUserId(userId);
        operationparticipation.setHelperId(operationId);
        operationparticipation.setEquipmentId(equipmentId);
        model.addAttribute("operationparticipation", operationparticipation);
        return "applyforequipment";
    }

    @PostMapping("/applyforequipment")
    public String submitEquipmentApplicationForm(@ModelAttribute Operationparticipation operationparticipation, @ModelAttribute Operation operation, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", operationparticipation.getHelperId());
        operationparticipation.setHelperId(null);
        operationparticipationRepository.save(operationparticipation);
        return "redirect:/detail";
    }

}
