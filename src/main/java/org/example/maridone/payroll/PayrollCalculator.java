package org.example.maridone.payroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.enums.ExemptionStatus;
import org.example.maridone.payroll.item.PayrollItem;
import org.example.maridone.payroll.item.component.DeductionsLine;
import org.example.maridone.payroll.item.component.DeductionsRepository;
import org.example.maridone.payroll.item.component.EarningsLine;
import org.example.maridone.payroll.item.component.EarningsRepository;
import org.example.maridone.schedule.shift.TemplateShiftSchedule;
import org.springframework.stereotype.Component;

@Component
public class PayrollCalculator {

    private final EarningsRepository earningsRepository;
    private final DeductionsRepository deductionsRepository;

    public PayrollCalculator(
        EarningsRepository earningsRepository,
        DeductionsRepository deductionsRepository
    )
    {
        this.earningsRepository = earningsRepository;
        this.deductionsRepository = deductionsRepository;
    }


    public List<EarningsLine> setEarnings(Employee emp, PayrollItem item) {
        List<EarningsLine> earnings =  new ArrayList<>();
        if (emp.getExemptionStatus().equals(ExemptionStatus.EXEMPT)) {
            EarningsLine earningsLine = new EarningsLine();
            earningsLine.setRate(BigDecimal.ZERO);
            earningsLine.setHours(BigDecimal.ZERO);
            earningsLine.setAmount(item.getGrossPay());
            earningsLine.setEarningsDate(LocalDate.now());
            earnings.add(earningsLine);
        } else if (emp.getExemptionStatus().equals(ExemptionStatus.NON_EXEMPT)) {

        }
        return earnings;
    }

    //todo: Non-exempt earnings logic
    public List<EarningsLine> setEarnings(Employee emp, PayrollItem item, List<EarningsLine> precomputedLines) {
        return precomputedLines == null ? List.of() : precomputedLines;
    }

    //todo: Deductions Logic (Exempt + Non-Exempt) using passed deduction components
    public List<DeductionsLine> setDeductions(
            Employee emp,
            PayrollItem item,
            BigDecimal absentDeductAmount,
            BigDecimal lateDeductAmount
    ) {
        return List.of();
    }


    public BigDecimal calculateHours(LocalTime start, LocalTime end) {
        Duration time = Duration.between(start,end);
        if (time.isNegative()) {
            time = time.plusDays(1);
        }

        return BigDecimal.valueOf(time.toMinutes()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateHours(List<TemplateShiftSchedule> schedules) {
        if (schedules.isEmpty() || schedules == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal hours = BigDecimal.ZERO;
        for (TemplateShiftSchedule schedule : schedules) {
            hours = hours.add(calculateHours(schedule.getStartTime(), schedule.getEndTime()));
        }
        return hours.setScale(2, RoundingMode.HALF_UP);
    }



}
