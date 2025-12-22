package org.example.maridone.payroll;

import jakarta.persistence.*;
import org.example.maridone.enums.Status;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "dispute_request")
public class DisputeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dispute_id", nullable = false)
    private Long disputeId;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "reason", nullable = true)
    private String reason;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private PayrollItem payrollItem;

    public Long getDisputeId() {
        return disputeId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PayrollItem getPayrollItem() {
        return payrollItem;
    }

    public void setPayrollItem(PayrollItem payrollItem) {
        this.payrollItem = payrollItem;
    }
}
