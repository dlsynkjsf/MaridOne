package org.example.maridone.payroll;

import jakarta.persistence.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.sql.ast.tree.expression.Over;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "earnings_line")
public class EarningsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "earnings_id", nullable = false)
    private Long earningsId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private PayrollItem payrollItem;

    @ManyToOne
    @JoinColumn(name = "overtime_id", nullable = true)
    private OvertimeRequest overtimeRequest;

    @Column(name = "hours", nullable = false)
    private BigDecimal hours;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public long getEarningsId() {
        return earningsId;
    }

    public PayrollItem getPayrollItem() {
        return payrollItem;
    }

    public void setPayrollItem(PayrollItem payrollItem) {
        this.payrollItem = payrollItem;
    }

    public OvertimeRequest getOvertimeRequest() {
        return overtimeRequest;
    }

    public void setOvertimeRequest(OvertimeRequest overtimeRequest) {
        this.overtimeRequest = overtimeRequest;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
