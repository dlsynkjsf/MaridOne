package org.example.maridone.core.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.example.maridone.marker.OnCreate;
import org.example.maridone.marker.OnUpdate;

public class BankAccountDto {
    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long bankId;
    @NotNull(groups = {OnCreate.class,  OnUpdate.class})
    private String accountNumber;
    @NotNull(groups = {OnCreate.class,  OnUpdate.class})
    private String bankName;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
