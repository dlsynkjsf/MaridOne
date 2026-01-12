package org.example.maridone.component;

import org.example.maridone.core.user.UserAccount;
import org.example.maridone.core.user.UserAccountRepository;
import org.springframework.stereotype.Component;


@Component("authCheck")
public class AuthorityCheck {

    private final UserAccountRepository userAccountRepository;
    public AuthorityCheck(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public boolean isSelf(Long id, String username) {
        UserAccount user =  userAccountRepository.findByUsername(username).orElse(null);
        if (user == null || user.getEmployee() == null) {
            return false;
        }
        Long userId = user.getEmployee().getEmployeeId();
        return userId.equals(id);
    }
}
