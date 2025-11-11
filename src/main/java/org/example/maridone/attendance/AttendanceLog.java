package org.example.maridone.attendance;

import jakarta.persistence.*;
import org.example.maridone.core.employee.Employee;

import java.time.Instant;

@Entity
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "direction")
    private String direction;

    public Long getAttendanceId() {
        return attendanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
