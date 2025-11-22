package org.example.maridone.core.employee;

import org.example.maridone.core.dto.BankAccountDto;
import org.example.maridone.core.dto.EmployeeRequest;
import org.example.maridone.core.dto.EmployeeResponse;
import org.example.maridone.core.dto.UserAccountDto;
import org.example.maridone.embeddable.Address;
import org.example.maridone.enums.EmploymentStatus;
import org.example.maridone.enums.Position;
import org.example.maridone.leave.LeaveRequest;
import org.example.maridone.notification.Notification;
import org.example.maridone.payroll.PayrollItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
        FOR MANAGEMENT
        MANAGEMENT ROLE REQUIRED
     */
    // ENDPOINT: api/employees/create
    @PostMapping("/create")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    // ENDPOINT: api/employees
    @GetMapping()
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // ENDPOINT: api/employees/id
    // id is dynamic
    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    // ENDPOINT: api/employees/id
    // id is dynamic
    //returns a status code instead of automatically showing it in the HRIS
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse response =  employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT: api/employees/search/{status}
    //status is dynamic
    //search employees by status
    @GetMapping("/search/{status}")
    public List<EmployeeResponse> searchByEmploymentStatus(@PathVariable EmploymentStatus status) {
        return employeeService.findByStatus(status);
    }

    // ENDPOINT: api/employees/search/{position}
    //position is dynamic
    //search employees by position
    @GetMapping("search/{position}")
    public List<EmployeeResponse> searchByPosition(@PathVariable Position position) {
        return employeeService.findByPosition(position);
    }

//    @PatchMapping("/{id}/update/status")
//    public EmployeeResponse updateStatus(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
//
//    }
//
//    @PatchMapping("/{id}/update/position")
//    public EmployeeResponse updatePosition(@PathVariable Long id, @RequestBody Position position) {
//
//    }






    /*
        FOR EMPLOYEE ROLE
        make sure only the specific employee can access their id
     */

//    @GetMapping("/{id}/bank-accounts")
//    public BankAccountDto getBankAccounts(@PathVariable Long id) {
//
//    }
//
//    @PostMapping("/{id}/bank-accounts")
//    public BankAccountDto createBankAccount(@PathVariable Long id, BankAccountDto bankDetails) {
//
//    }
//
//    @GetMapping("/{id}/notifications")
//    public List<Notification> getAllNotifications(@PathVariable Long id) {
//
//    }
//
//    @GetMapping("/{id}/leave-requests")
//    public List<LeaveRequest> getAllLeaveRequests(@PathVariable Long id) {
//
//    }
//
//    @GetMapping("{id}/leave-balance")
//    public BigDecimal getLeaveBalance(@PathVariable Long id) {
//
//    }
//
//    @GetMapping("/{id}/payroll-items")
//    public List<PayrollItem> getAllPayrollItems(@PathVariable Long id) {
//
//    }
//
//    @GetMapping("/{id}/user-account")
//    public UserAccountDto getUserAccount(@PathVariable Long id) {
//
//    }
//
//    @PatchMapping("/{id}/update/address")
//    public EmployeeResponse updateAddress(@PathVariable Long id, Address address) {
//
//    }

}
