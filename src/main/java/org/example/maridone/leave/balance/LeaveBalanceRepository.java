package org.example.maridone.leave.balance;

import org.example.maridone.enums.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long>, JpaSpecificationExecutor<LeaveBalance> {
    LeaveBalance findByEmployee_EmployeeIdAndLeaveType(Long employeeEmployeeId, LeaveType leaveType);

    List<LeaveBalance> findByEmployee_EmployeeId(Long employeeId);
}
