package org.example.maridone.calendar;

import jakarta.persistence.*;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.enums.EarningsType;

import java.time.OffsetTime;

@Entity
public class ShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shift_id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(name = "title")
    private String title;

    @Column(name = "start_time")
    private OffsetTime startTime;

    @Column(name = "end_time")
    private OffsetTime endTime;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @Column(name = "shift_type")
    @Enumerated(EnumType.STRING)
    private EarningsType earningsType;

    public Long getShift_id() {
        return shift_id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OffsetTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetTime startTime) {
        this.startTime = startTime;
    }

    public OffsetTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetTime endTime) {
        this.endTime = endTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public EarningsType getEarningsType() {
        return earningsType;
    }

    public void setEarningsType(EarningsType earningsType) {
        this.earningsType = earningsType;
    }
}
