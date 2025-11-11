package org.example.maridone.payroll;


import jakarta.persistence.*;
import org.example.maridone.core.Employee;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "payroll_item")
public class PayrollItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "pay_id", nullable = false)
    private PayrollRun payrollRun;

    @Column(name = "gross_pay")
    private BigDecimal grossPay;

    @Column(name = "net_pay")
    private BigDecimal netPay;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<DeductionsLine> deductions;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<EarningsLine> earnings;

    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<DisputeRequest> disputes;

    public long getItemId() {
        return itemId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PayrollRun getPayrollRun() {
        return payrollRun;
    }

    public void setPayrollRun(PayrollRun payrollRun) {
        this.payrollRun = payrollRun;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(BigDecimal grossPay) {
        this.grossPay = grossPay;
    }

    public BigDecimal getNetPay() {
        return netPay;
    }

    public void setNetPay(BigDecimal netPay) {
        this.netPay = netPay;
    }

    public List<DeductionsLine> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<DeductionsLine> deductions) {
        this.deductions = deductions;
    }

    public List<EarningsLine> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<EarningsLine> earnings) {
        this.earnings = earnings;
    }
}
