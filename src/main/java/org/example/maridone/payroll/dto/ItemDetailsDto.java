package org.example.maridone.payroll.dto;
import java.math.BigDecimal;

public class ItemDetailsDto {
    private Long id;
    private RunDetailsDto runDetails;
    private BigDecimal grossPay;
    private BigDecimal netPay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RunDetailsDto getRunDetails() {
        return runDetails;
    }

    public void setRunDetails(RunDetailsDto runDetails) {
        this.runDetails = runDetails;
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
}
