package org.example.maridone.overtime.dto;

import org.example.maridone.enums.Status;

public class OvertimeUpdateDto {
    private Long overtimeId;
    private Status updateStatus;
    private String approveReason;

    public Long getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(Long overtimeId) {
        this.overtimeId = overtimeId;
    }

    public Status getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Status updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getApproveReason() {
        return approveReason;
    }
    public void setApproveReason(String approveReason) {
        this.approveReason = approveReason;
    }
}
