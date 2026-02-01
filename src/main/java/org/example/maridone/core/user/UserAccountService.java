package org.example.maridone.core.user;

import org.example.maridone.core.dto.CreateUserAccountDto;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.core.mapper.CoreMapper;
import org.example.maridone.enums.Position;
import org.example.maridone.exception.AccountNotFoundException;
import org.example.maridone.exception.BadCredentialsException;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final CoreMapper coreMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    public UserAccountService
            (UserAccountRepository userAccountRepository,
             CoreMapper coreMapper,
             PasswordEncoder passwordEncoder,
             EmployeeRepository employeeRepository) {
        this.userAccountRepository = userAccountRepository;
        this.coreMapper = coreMapper;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    public UserAccount getUserAccount(String username) {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(()-> new AccountNotFoundException(username));
    }


    @Transactional
    public UserAccount createUserAccount(CreateUserAccountDto payload) {
        UserAccount user = new UserAccount();
        Employee emp = employeeRepository.findById(payload.getEmployeeId()).orElseThrow(() -> new EmployeeNotFoundException(payload.getEmployeeId()));
        user.setUsername(payload.getUsername());
        user.setPasswordHash(passwordEncoder.encode(payload.getPassword()));
        user.setEmployee(emp);
        user.setAccountStatus(payload.getAccountStatus());
        userAccountRepository.save(user);
        return user;
    }
}
