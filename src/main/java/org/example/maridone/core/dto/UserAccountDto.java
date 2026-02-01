package org.example.maridone.core.dto;

import org.example.maridone.enums.AccountStatus;
import org.example.maridone.enums.Position;

public class UserAccountDto {

    private String username;
    private AccountStatus accountStatus;
    private Position role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Position getRole() {
        return role;
    }

    public void setRole(Position role) {
        this.role = role;
    }
}
