package org.example.maridone.sampledata;

import org.example.maridone.core.bank.BankAccount;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.user.UserAccount;
import org.example.maridone.embeddable.Address;
import org.example.maridone.enums.*;
import org.example.maridone.leave.LeaveBalance;
import org.example.maridone.leave.LeaveRequest;
import org.example.maridone.notification.Notification;
import org.example.maridone.payroll.DeductionsLine;
import org.example.maridone.payroll.EarningsLine;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.PayrollRun;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class SampleRows {

    @PostConstruct
    public void init() {
        sampleData();
    }

    public static void sampleData() {

    }
}

