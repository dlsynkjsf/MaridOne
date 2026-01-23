package org.example.maridone.leave.balance;

import org.example.maridone.enums.LeaveType;

import java.math.BigDecimal;

public class BalanceResponseDto {
    private Long leaveId;
    private BigDecimal balanceHours;
    private LeaveType leaveType;

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public BigDecimal getBalanceHours() {
        return balanceHours;
    }

    public void setBalanceHours(BigDecimal balanceHours) {
        this.balanceHours = balanceHours;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}
