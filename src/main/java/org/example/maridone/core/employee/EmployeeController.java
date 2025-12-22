package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeRequest;
import org.example.maridone.core.dto.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    //update to: status 201: created
    //update to: responseentity
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse response = employeeService.createEmployee(employeeRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
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
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse response =  employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT: api/employees/status/id
    // id is dynamic
    @PatchMapping("status/{id}")
    public EmployeeResponse updateStatus(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateStatus(id, employeeRequest);
    }

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
