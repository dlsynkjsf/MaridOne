package org.example.maridone.payroll.run;

import org.example.maridone.payroll.dto.ItemDetailsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/payroll")
public class PayrollController {
    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping("/item/{empId}")
    public List<ItemDetailsDto> getItems(@PathVariable Long empId) {
        return payrollService.getItems(empId);
    }
}
