package org.example.maridone.schedule.shift;


import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.enums.EarningsType;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.example.maridone.exception.ShiftsNotFoundException;
import org.example.maridone.schedule.dto.ShiftRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    public ShiftService(
            ShiftRepository shiftRepository,
            EmployeeRepository employeeRepository) {
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
    }

    //create all for 7 days [monday, ...., sunday]
    //used for onboarding ONLY
    @Transactional
    public void createShifts(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
        List<ShiftSchedule> schedules = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ShiftSchedule shiftSchedule = new ShiftSchedule();
            shiftSchedule.setEmployee(emp);
            shiftSchedule.setStartTime(LocalTime.now());
            shiftSchedule.setEndTime(LocalTime.now());
            shiftSchedule.setDayOfWeek(DayOfWeek.of(i+1));
            shiftSchedule.setEarningsType(EarningsType.BASIC);
            schedules.add(shiftSchedule);
        }
        shiftRepository.saveAll(schedules);
    }


    public List<ShiftSchedule> getShiftSchedule(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
        List<ShiftSchedule> schedules = shiftRepository.findAllByEmployeeId(empId);
        if (schedules.isEmpty()) {
            throw new ShiftsNotFoundException("Employee ID: " + empId + "has no schedule.");
        };
        return shiftRepository.findAllByEmployeeId(empId);
    }

    @Transactional
    public ShiftSchedule addShiftSchedule(ShiftRequestDto payload) {
        Employee emp = employeeRepository.findById(payload.getEmpId()).orElseThrow(() -> new EmployeeNotFoundException(payload.getEmpId()));
        ShiftSchedule shiftSchedule = new ShiftSchedule();
        shiftSchedule.setEmployee(emp);
        shiftSchedule.setStartTime(payload.getStartTime());
        shiftSchedule.setEndTime(payload.getEndTime());
        shiftSchedule.setDayOfWeek(payload.getDayOfWeek());
        shiftSchedule.setEarningsType(payload.getEarningsType());
        shiftRepository.save(shiftSchedule);
        return shiftSchedule;
    }

    @Transactional
    public ShiftSchedule updateShiftSchedule(ShiftRequestDto payload) {
        ShiftSchedule schedule = shiftRepository.findById(payload.getShiftId()).orElseThrow(() -> new ShiftsNotFoundException("Shift of ID: " + payload.getShiftId() +" not found."));
        schedule.setStartTime(payload.getStartTime());
        schedule.setEndTime(payload.getEndTime());
        schedule.setDayOfWeek(payload.getDayOfWeek());
        schedule.setEarningsType(payload.getEarningsType());
        shiftRepository.save(schedule);
        return schedule;
    }
}
