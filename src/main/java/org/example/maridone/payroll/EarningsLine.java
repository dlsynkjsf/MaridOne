package org.example.maridone.payroll;

import jakarta.persistence.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.math.BigDecimal;

@Entity
@Table(name = "earnings_line")
public class EarningsLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "earnings_id")
    private Long earningsId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private PayrollItem itemId;

    @Column(name = "hours")
    private double hours;

    @Column(name = "rate")
    private double rate;

    @Column(name = "amount")
    private BigDecimal amount;

    public long getEarningsId() {
        return earningsId;
    }

    public PayrollItem getItemId() {
        return itemId;
    }

    public void setItemId(PayrollItem itemId) {
        this.itemId = itemId;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
