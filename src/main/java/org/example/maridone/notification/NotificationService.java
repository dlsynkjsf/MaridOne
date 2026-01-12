package org.example.maridone.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Page<Notification> getUserNotifications(String username, Pageable pageable) {
        return notificationRepository.findByEmployee_UserAccount_Username(username, pageable);
    }

    public Page<Notification> getNewUserNotifications(String username, Pageable pageable) {
        return notificationRepository.findByEmployee_UserAccount_UsernameAndReadStatusFalse(username, pageable);
    }
}
