package org.example.maridone.payroll.run;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.dto.ItemDetailsDto;
import org.example.maridone.payroll.mapper.PayrollMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRunRepository payrollRunRepository;
    private final PayrollItemRepository payrollItemRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollMapper payrollMapper;

    PayrollService(
            PayrollRunRepository payrollRunRepository,
            PayrollItemRepository payrollItemRepository,
            EmployeeRepository employeeRepository,
            PayrollMapper payrollMapper
    )
    {
        this.payrollRunRepository = payrollRunRepository;
        this.payrollItemRepository = payrollItemRepository;
        this.employeeRepository = employeeRepository;
        this.payrollMapper = payrollMapper;
    }

    @Transactional
    public List<ItemDetailsDto> getItems(Long empId) {
        List<PayrollItem> items = payrollItemRepository.findByEmployee_EmployeeId(empId);
        List<ItemDetailsDto> itemsDto = payrollMapper.toItemDetailsDtos(items);

        return itemsDto;
    }


    //all methods must be locked pessimistically
}
