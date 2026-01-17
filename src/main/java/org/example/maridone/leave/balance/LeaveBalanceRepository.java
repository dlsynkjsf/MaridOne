package org.example.maridone.leave.balance;

import org.example.maridone.enums.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
    LeaveBalance findByEmployee_EmployeeIdAndLeaveType(Long employeeEmployeeId, LeaveType leaveType);
}
