package org.example.maridone.core.user;

import org.example.maridone.core.mapper.CoreMapper;
import org.example.maridone.enums.Position;
import org.example.maridone.exception.AccountNotFoundException;
import org.example.maridone.exception.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final CoreMapper coreMapper;
    private final PasswordEncoder passwordEncoder;

    public UserAccountService
            (UserAccountRepository userAccountRepository,
             CoreMapper coreMapper,
             PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.coreMapper = coreMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserAccount getUserAccount(String username) {
        UserAccount user = userAccountRepository.findByUsername(username)
                .orElseThrow(()-> new AccountNotFoundException(username));
        return user;
    }


    

}
