package org.example.maridone.leave;


import jakarta.persistence.*;
import org.example.maridone.core.employee.Employee;

import java.math.BigDecimal;

@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Column(name ="balance_remaining", nullable = false)
    private BigDecimal balanceRemaining;

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
