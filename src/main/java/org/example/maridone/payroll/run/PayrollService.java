package org.example.maridone.payroll.run;

import org.example.maridone.core.employee.EmployeeService;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.itemcomponent.DeductionsService;
import org.example.maridone.payroll.dto.ItemDetailsDto;
import org.example.maridone.payroll.itemcomponent.EarningsService;
import org.example.maridone.payroll.mapper.PayrollMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PayrollService {

    private final PayrollRunRepository payrollRunRepository;
    private final PayrollItemRepository payrollItemRepository;
    private final EmployeeService employeeService;
    private final EarningsService earningsService;
    private final DeductionsService deductionsService;
    private final PayrollMapper payrollMapper;

    PayrollService(
            PayrollRunRepository payrollRunRepository,
            PayrollItemRepository payrollItemRepository,
            EmployeeService employeeService,
            EarningsService earningsService,
            DeductionsService deductionsService,
            PayrollMapper payrollMapper
    )
    {
        this.payrollRunRepository = payrollRunRepository;
        this.payrollItemRepository = payrollItemRepository;
        this.employeeService = employeeService;
        this.earningsService = earningsService;
        this.deductionsService = deductionsService;
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
