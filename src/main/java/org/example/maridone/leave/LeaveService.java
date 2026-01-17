package org.example.maridone.leave;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.exception.DuplicateLeaveException;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.example.maridone.leave.balance.BalanceRequestDto;
import org.example.maridone.leave.balance.LeaveBalance;
import org.example.maridone.leave.balance.LeaveBalanceRepository;
import org.example.maridone.leave.request.LeaveRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaveService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    public LeaveService(
            LeaveBalanceRepository leaveBalanceRepository,
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            LeaveMapper leaveMapper) {
        this.leaveBalanceRepository = leaveBalanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }

    @Transactional
    public LeaveBalance createLeave(Long empId, BalanceRequestDto payload) {
        LeaveBalance check = leaveBalanceRepository.findByEmployee_EmployeeIdAndLeaveType(empId, payload.getLeaveType());
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
        if (check != null) {
            throw new DuplicateLeaveException(empId, payload.getLeaveType());
        }
//
//        LeaveBalance create = new LeaveBalance();
//        create.setEmployee(emp);
//        create.setLeaveType(payload.getLeaveType());
//        create.setBalanceHours(payload.getBalanceHours());
        LeaveBalance create = leaveBalanceRepository.save(leaveMapper.toEntity(payload, emp));
        return create;
    }
}
