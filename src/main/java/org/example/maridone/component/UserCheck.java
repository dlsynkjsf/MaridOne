package org.example.maridone.component;

import org.example.maridone.core.user.UserAccount;
import org.example.maridone.core.user.UserAccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component("userCheck")
public class UserCheck implements CheckerInterface {

    private final UserAccountRepository userAccountRepository;
    public UserCheck(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public boolean isSelf(Long empId, String username) {
        UserAccount user =  userAccountRepository.findByUsername(username).orElse(null);
        if (user == null || user.getEmployee() == null) {
            return false;
        }
        Long userId = user.getEmployee().getEmployeeId();
        return userId.equals(empId);
    }
}
