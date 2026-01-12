package org.example.maridone.notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //finds by traversing through Employee -> UserAccount -> UserAccount field username
    Page<Notification> findByEmployee_UserAccount_Username(String username,  Pageable pageable);

    Page<Notification> findByEmployee_UserAccount_UsernameAndReadStatusFalse(String username,  Pageable pageable);
}
