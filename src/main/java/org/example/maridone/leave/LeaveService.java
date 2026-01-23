package org.example.maridone.leave;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.exception.DuplicateLeaveException;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.example.maridone.exception.InsufficientBalanceException;
import org.example.maridone.exception.LeaveNotFoundException;
import org.example.maridone.leave.balance.*;
import org.example.maridone.leave.request.LeaveRequestRepository;
import org.example.maridone.leave.spec.LeaveSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Specification<LeaveBalance> spec = Specification.allOf(
                LeaveSpecs.hasEmployeeId(empId),
                LeaveSpecs.hasLeaveType(payload.getLeaveType())
        );
        Optional<LeaveBalance> check = leaveBalanceRepository.findOne(spec);
        Employee emp = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException(empId));
        if (check.isPresent()) {
            throw new DuplicateLeaveException(empId, payload.getLeaveType());
        }

        LeaveBalance create = leaveBalanceRepository.save(leaveMapper.toEntity(payload, emp));
        return create;
    }

    public List<BalanceResponseDto> getBalance(Long empId) {
        return leaveMapper.toBalanceResponsesDto(leaveBalanceRepository.findByEmployee_EmployeeId(empId));
    }


    @Transactional
    public void updateBalance(Long empId, UpdateBalanceDto payload) {
        Specification<LeaveBalance> spec = Specification.allOf(
                LeaveSpecs.hasEmployeeId(empId),
                LeaveSpecs.hasLeaveType(payload.getLeaveType())
        );
        LeaveBalance lb = leaveBalanceRepository.findOne(spec).orElseThrow(() -> new LeaveNotFoundException
                (
                "Employee Id: " + empId + " has no Leave of type: " + payload.getLeaveType()
                        + " Please create a leave type " + payload.getLeaveType() + " for Employee " + empId
                )
        );

        if (payload.getType().equalsIgnoreCase("add")) {
            lb.setBalanceHours(lb.getBalanceHours().add(payload.getBalanceHours()));
        } else {
            if (lb.getBalanceHours().compareTo(payload.getBalanceHours()) < 0) {
                String message = "Balance hours for Employee Id: " + empId + " is insufficient."
                        + " Remaining Balance = " + lb.getBalanceHours() + ", "
                        + "Requested = " +  payload.getBalanceHours();
                throw new InsufficientBalanceException(message);
            }
            lb.setBalanceHours(lb.getBalanceHours().subtract(payload.getBalanceHours()));
        }
        leaveBalanceRepository.save(lb);
    }
}
