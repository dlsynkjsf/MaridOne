package org.example.maridone.payroll;

import jakarta.persistence.*;
import org.example.maridone.enums.DisputeStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "dispute_request")
public class DisputeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dispute_id")
    private Long disputeId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DisputeStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private PayrollItem payrollItem;

    public Long getDisputeId() {
        return disputeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DisputeStatus getStatus() {
        return status;
    }

    public void setStatus(DisputeStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PayrollItem getPayrollItem() {
        return payrollItem;
    }

    public void setPayrollItem(PayrollItem payrollItem) {
        this.payrollItem = payrollItem;
    }
}
