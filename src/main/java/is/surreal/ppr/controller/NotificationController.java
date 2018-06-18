package is.surreal.ppr.controller;

import is.surreal.ppr.model.*;
import is.surreal.ppr.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081", exposedHeaders = "Location")
@RequestMapping("/rest/notification")
public class NotificationController {

    private NotificationRepository notificationRepository;
    private OperationRepository operationRepository;
    private UserRepository userRepository;
    private OperationParticipationRepository operationParticipationRepository;
    private ReadRepository readRepository;


    @Autowired
    public NotificationController(ReadRepository readRepository, OperationRepository operationRepository, NotificationRepository notificationRepository, UserRepository userRepository, OperationParticipationRepository operationParticipationRepository) {
        this.notificationRepository = notificationRepository;
        this.operationRepository = operationRepository;
        this.operationParticipationRepository = operationParticipationRepository;
        this.userRepository = userRepository;
        this.readRepository = readRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    Iterable<Notification> getNewNotifications() {
        Set<Notification> notificationHashSet = new HashSet<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(auth.getName());
        Collection<Operationparticipation> operationparticipations = user.getOperationparticipationsById();
        for (Operationparticipation operationparticipation : operationparticipations) {
            if (operationparticipation.getAccepted() != null && operationparticipation.getAccepted() != 0) {
                Operation operation;
                if (operationparticipation.getHelperId() != null) {
                    operation = operationparticipation.getHelperByHelperId().getOperationByOperationId();
                } else {
                    operation = operationparticipation.getEquipmentByEquipmentId().getOperationByOperationId();
                }
                notificationHashSet.addAll(operation.getNotificationsById());
            }
        }
        Collection<Notificationread> notificationreads = user.getReadsById();
        for (Notificationread notificationread : notificationreads) {
            notificationHashSet.remove(notificationread.getMessageByMessageId());
        }
        List<Notification> notifications = new ArrayList<>();
        notifications.addAll(notificationHashSet);
        return notifications;
    }

}
