package org.example.maridone.payroll.dto;

import org.example.maridone.enums.DeductionType;

import java.math.BigDecimal;

public class DeductionsDto {

    private Long deductionsId;
    private DeductionType deductionType;
    private BigDecimal amount;

    public Long getDeductionsId() {
        return deductionsId;
    }

    public void setDeductionsId(Long deductionsId) {
        this.deductionsId = deductionsId;
    }

    public DeductionType getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(DeductionType deductionType) {
        this.deductionType = deductionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
