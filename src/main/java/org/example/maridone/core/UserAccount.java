package org.example.maridone.core;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.maridone.enums.AccountStatus;

@Entity
public class UserAccount {

    @Id
    @Column(name = "username", unique = true, nullable = false)
    @Size(min = 4, max = 30)
    private String username;

    @Column(name = "password_hash",  nullable = false)
    private String passwordHash;

    @Column(name = "account_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id",nullable = false)
    private Employee employee;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
