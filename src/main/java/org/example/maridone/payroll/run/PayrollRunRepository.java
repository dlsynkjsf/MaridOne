package org.example.maridone.payroll.run;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRunRepository extends JpaRepository<PayrollRun, Long> {


}
