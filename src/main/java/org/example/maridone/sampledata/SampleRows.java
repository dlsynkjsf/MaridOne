package org.example.maridone.sampledata;

import org.example.maridone.core.bank.BankAccount;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.employee.EmployeeRepository;
import org.example.maridone.core.user.UserAccount;
import org.example.maridone.embeddable.Address;
import org.example.maridone.enums.*;
import org.example.maridone.leave.LeaveBalance;
import org.example.maridone.leave.LeaveRequest;
import org.example.maridone.notification.Notification;
import org.example.maridone.payroll.DeductionsLine;
import org.example.maridone.payroll.EarningsLine;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.run.PayrollRun;
import org.example.maridone.payroll.run.PayrollRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PayrollRunRepository payrollRunRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        sampleData();
    }

    public void sampleData() {

        List<Employee> employees = new ArrayList<>();
        List<PayrollItem> items = new ArrayList<>();
        PayrollRun payrollRun = new PayrollRun();

        payrollRun.setPayrollStatus(PayrollStatus.IN_PROGRESS);
        payrollRun.setRunType(RunType.REGULAR);
        payrollRun.setPeriodStart(LocalDate.now().minusDays(14));
        payrollRun.setPeriodEnd(LocalDate.now());
        payrollRunRepository.save(payrollRun);

        for (int i = 0; i < 10; i++) {
            Employee emp = new Employee();
            LocalDate birthDate = LocalDate.of(2000, 10, i+1);
            Address address = new Address();



            emp.setFirstName("firstName" + i);
            if (i == 5 || i == 8) {
                emp.setMiddleName("middleName" + i);
            }
            emp.setLastName("lastName" + (i*2));
            emp.setBirthDate(birthDate);
            switch(i) {
                case 1: {
                    emp.setEmploymentStatus(EmploymentStatus.REGULAR);
                    emp.setPosition(Position.MANAGEMENT);
                    break;
                }
                case 2: {
                    emp.setEmploymentStatus(EmploymentStatus.PART_TIME);
                    emp.setPosition(Position.HR);
                    break;
                }
                case 3: {
                    emp.setEmploymentStatus(EmploymentStatus.SUSPENDED);
                    emp.setPosition(Position.ACCOUNTING);
                    break;
                }
                case 4: {
                    emp.setEmploymentStatus(EmploymentStatus.TERMINATED);
                    emp.setPosition(Position.EMPLOYEE);
                    break;
                }
                default: {
                    emp.setEmploymentStatus(EmploymentStatus.REGULAR);
                    emp.setPosition(Position.EMPLOYEE);
                }
            }

            emp.setEmail("email" + i + "@gmail.com");
            emp.setPhoneNumber("0901500009" + i);

            address.setPermanentAddress("permanentAddress" + i);
            address.setCity("city" + i);
            address.setState("state" + i);
            address.setZipCode("174" + i);
            address.setCountry("Philippines");

            if (i == 4) {
                address.setTemporaryAddress("temporaryAddress" + i);
            }

            emp.setAddress(address);



            List<BankAccount> bankAccounts = createBankAccounts(emp, i);
            emp.setBankAccounts(bankAccounts);

            UserAccount userAccount = new UserAccount();
            userAccount.setAccountStatus(AccountStatus.ACTIVE);
            userAccount.setEmployee(emp);
            userAccount.setPasswordHash(passwordEncoder.encode("test"));
            userAccount.setUsername("userName" + i);
            emp.setUserAccount(userAccount);

            PayrollItem item = createPayrollItem(emp, i, payrollRun);
            items.add(item);
            emp.setPayrollItems(new ArrayList<PayrollItem>());
            emp.getPayrollItems().add(item);

            List<Notification> notifications = createNotifications(emp, i);
            emp.setNotifications(notifications);

            List<LeaveRequest> leaveRequests = createLeave(emp, i);
            emp.setRequests(leaveRequests);

            LeaveBalance leaveBalance = createBalance(emp, i);
            emp.setLeaveBalance(leaveBalance);

            employeeRepository.save(emp);
        }

        payrollRun.setItems(items);
    }

    public static List<BankAccount> createBankAccounts(Employee emp, int i) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount.setBankName("BPI");
        bankAccount2.setBankName("BDO");

        bankAccount.setAccountNumber(String.valueOf(i));
        bankAccount2.setAccountNumber(String.valueOf(i*10));

        bankAccount.setEmployee(emp);
        bankAccount2.setEmployee(emp);

        bankAccounts.add(bankAccount);
        bankAccounts.add(bankAccount2);

        return bankAccounts;
    }

    public static List<Notification> createNotifications(Employee emp, int i) {

        List<Notification> notifications = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Notification notification = new Notification();
            notification.setMessage("Sample notification message " + j);
            notification.setCreatedAt(LocalDateTime.now().minusDays(i));
            notification.setReadStatus(j % 2 == 0);
            notification.setEmployee(emp);
            notifications.add(notification);
        }
        return notifications;
    }

    public static List<LeaveRequest> createLeave(Employee emp, int i) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(emp);


        leaveRequest.setStartDate(LocalDate.now().plusDays(i));
        leaveRequest.setEndDate(LocalDate.now().plusDays(i + 2));

        leaveRequest.setApprover("Manager " + i);


        leaveRequest.setRequestStatus(i % 2 == 0 ? "Approved" : "Pending");


        leaveRequest.setReason("Vacation request #" + i);


        leaveRequests.add(leaveRequest);
        return leaveRequests;
    }

    public static LeaveBalance createBalance(Employee emp, int i) {
        return new LeaveBalance(emp, BigDecimal.valueOf(5.22));
    }

    public static PayrollItem createPayrollItem(Employee emp, int i, PayrollRun payrollRun) {
        PayrollItem payrollItem = new PayrollItem();

        for (int j = 0; j < 5; j++) {
            payrollItem.setEmployee(emp);
            payrollItem.setPayrollRun(payrollRun);
            payrollItem.setGrossPay(BigDecimal.valueOf(5000));
            payrollItem.setNetPay(BigDecimal.valueOf(5000));
            payrollItem.setDeductions(new ArrayList<>());
            payrollItem.setEarnings(new ArrayList<>());
            payrollItem.setDisputes(new ArrayList<>());
        }

        return payrollItem;
    }

    public static List<DeductionsLine> deductionLines(Employee emp, int i) {
        List<DeductionsLine> deductionLines = new ArrayList<>();

        return deductionLines;
    }

    public static List<EarningsLine> earningLines(Employee emp, int i) {
        List<EarningsLine> earningLines = new ArrayList<>();

        return earningLines;
    }
}

