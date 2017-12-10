package is.surreal.ppr.controller;

import is.surreal.ppr.model.Operationparticipation;
import is.surreal.ppr.repository.OperationparticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationController {

    @Autowired
    OperationparticipationRepository operationparticipationRepository;

    @RequestMapping("/applyforhelper")
    public String applyForHelper(@RequestParam int userId, @RequestParam int helperId, @RequestParam int operationId, RedirectAttributes redirectAttributes) {
        Operationparticipation operationparticipation = new Operationparticipation();
        operationparticipation.setUserId(userId);
        operationparticipation.setHelperId(helperId);
        operationparticipationRepository.save(operationparticipation);
        redirectAttributes.addAttribute("id", operationId);
        return "redirect:/detail";
    }

    @RequestMapping("/applyforequipment")
    public String applyForEquipment(@RequestParam int userId, @RequestParam int equipmentId, @RequestParam int operationId, RedirectAttributes redirectAttributes) {
        Operationparticipation operationparticipation = new Operationparticipation();
        operationparticipation.setUserId(userId);
        operationparticipation.setEquipmentId(equipmentId);
        operationparticipationRepository.save(operationparticipation);
        redirectAttributes.addAttribute("id", operationId);
        return "redirect:/detail";
    }

}
