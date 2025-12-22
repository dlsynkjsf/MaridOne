package org.example.maridone.payroll;

import jakarta.persistence.*;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.enums.EarningsType;
import org.example.maridone.enums.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "overtime_request")
public class OvertimeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "overtime_id", nullable = false)
    private Long overtimeId;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "earningsId", cascade = CascadeType.ALL)
    private List<EarningsLine> lines;

    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    private Status requestStatus;

    @Column(name = "request_at", nullable = false)
    private Instant requestAt;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "hours")
    private BigDecimal hours;

    @Column(name = "overtime_type")
    private EarningsType overtimeType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "approver")
    private String approver;

    @Column(name = "approved_at")
    private Instant approvedAt;

    public Long getOvertimeId() {
        return overtimeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<EarningsLine> getLines() {
        return lines;
    }

    public void setLines(List<EarningsLine> lines) {
        this.lines = lines;
    }

    public Status getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Status requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public EarningsType getOvertimeType() {
        return overtimeType;
    }

    public void setOvertimeType(EarningsType overtimeType) {
        this.overtimeType = overtimeType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Instant getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(Instant requestAt) {
        this.requestAt = requestAt;
    }

    public Instant getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Instant approvedAt) {
        this.approvedAt = approvedAt;
    }
}
