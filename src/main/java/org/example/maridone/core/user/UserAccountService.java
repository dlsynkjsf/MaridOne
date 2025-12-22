package org.example.maridone.core.user;

import org.example.maridone.core.mapper.UserAccountMapper;
import org.example.maridone.enums.Position;
import org.example.maridone.exception.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService
            (UserAccountRepository userAccountRepository,
             UserAccountMapper userAccountMapper,
             PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    

}
