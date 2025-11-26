package org.example.maridone.log;

import jakarta.persistence.*;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.enums.Activity;

import java.time.Instant;

@Entity
public class ActivityLog {
    @Id
    @Column(name = "activity_id")
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    Employee employee;

    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    private Activity activityType;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    private Instant timestamp;

    public Long getActivityId() {
        return activityId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Activity getActivityType() {
        return activityType;
    }

    public void setActivityType(Activity activityType) {
        this.activityType = activityType;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
