package org.example.maridone.payroll.run;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayrollRunController {
    private final PayrollRunService payrollRunService;


    public PayrollRunController(PayrollRunService payrollRunService) {
        this.payrollRunService = payrollRunService;
    }
}
