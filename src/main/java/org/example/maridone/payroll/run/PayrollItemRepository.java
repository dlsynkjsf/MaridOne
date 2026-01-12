package org.example.maridone.payroll.run;

import org.example.maridone.payroll.PayrollItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollItemRepository extends JpaRepository<PayrollItem, Long> {

    List<PayrollItem> findByEmployee_EmployeeId(Long empId);
}
