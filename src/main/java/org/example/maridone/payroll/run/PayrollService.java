package org.example.maridone.payroll.run;

import org.example.maridone.core.employee.EmployeeService;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.itemcomponent.DeductionsService;
import org.example.maridone.payroll.dto.ItemDetailsDto;
import org.example.maridone.payroll.itemcomponent.EarningsService;
import org.example.maridone.payroll.mapper.PayrollMapper;
import org.example.maridone.payroll.spec.ItemSpecs;
import org.springframework.data.jpa.domain.Specification;
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

        Specification<PayrollItem> spec = Specification.allOf(
                ItemSpecs.hasEmployeeId(empId)
        );
        List<PayrollItem> items = payrollItemRepository.findAll(spec);

        return payrollMapper.toItemDetailsDtos(items);
    }



    //all methods must be locked pessimistically
}
