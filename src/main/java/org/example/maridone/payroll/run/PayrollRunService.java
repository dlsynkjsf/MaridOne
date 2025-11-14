package org.example.maridone.payroll.run;

import org.springframework.stereotype.Service;

@Service
public class PayrollRunService {

    private final PayrollRunRepository payrollRunRepository;

    PayrollRunService(PayrollRunRepository payrollRunRepository) {
        this.payrollRunRepository = payrollRunRepository;
    }
}
