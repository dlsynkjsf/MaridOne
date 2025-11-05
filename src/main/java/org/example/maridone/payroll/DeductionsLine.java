package org.example.maridone.payroll;
import jakarta.persistence.*;
import org.example.maridone.enums.DeductionType;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;


public class DeductionsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deductions_id")
    private long deductionsId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private PayrollItem itemId;

    @Column(name = "deduction_type")
    @Enumerated(EnumType.STRING)
    private DeductionType deductionType;

    @Column(name = "amount")
    private BigDecimal amount;


    public long getDeductionsId() {
        return deductionsId;
    }

    public PayrollItem getItemId() {
        return itemId;
    }

    public void setItemId(PayrollItem itemId) {
        this.itemId = itemId;
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
