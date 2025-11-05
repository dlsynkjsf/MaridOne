package org.example.maridone.leave;


import jakarta.persistence.*;
import org.example.maridone.core.Employee;

import java.math.BigDecimal;

@Entity
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "balance_id")
    private long balanceId;

    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(name ="balance_remaining")
    private BigDecimal balanceRemaining;


    public long getBalanceId() {
        return balanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getBalanceRemaining() {
        return balanceRemaining;
    }

    public void setBalanceRemaining(BigDecimal balanceRemaining) {
        this.balanceRemaining = balanceRemaining;
    }

}
