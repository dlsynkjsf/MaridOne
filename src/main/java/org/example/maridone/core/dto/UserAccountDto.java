package org.example.maridone.core.dto;

import org.example.maridone.enums.AccountStatus;

public class UserAccountDto {

    private String username;
    private AccountStatus accountStatus;

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
}
