package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeDetailsDto;
import org.example.maridone.core.dto.EmployeeRequestDto;
import org.example.maridone.core.dto.EmployeeResponseDto;
import org.example.maridone.core.spec.EmployeeFilter;
import org.example.maridone.core.user.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserAccountService userAccountService;

    public EmployeeController(EmployeeService employeeService,
                              UserAccountService userAccountService) {
        this.employeeService = employeeService;
        this.userAccountService = userAccountService;
    }

    /*
        FOR MANAGEMENT
        MANAGEMENT ROLE REQUIRED
     */
    // ENDPOINT: api/employees/create
    @PostMapping("/create")
    @PreAuthorize("hasRole(MANAGEMENT)")
    //update to: status 201: created
    //update to: responseentity
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto response = employeeService.createEmployee(employeeRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    // ENDPOINT: api/employees
    @PreAuthorize("hasRole('MANAGEMENT')")
    @GetMapping()
    public Page<EmployeeResponseDto> getAllEmployees(
            EmployeeFilter employeeFilter,
            @PageableDefault(sort = "employeeId", direction = Sort.Direction.ASC) Pageable pageable) {
        return employeeService.getAllEmployees(employeeFilter,pageable);
    }

    // ENDPOINT: api/employees/self/id
    // for personal details check only
    @GetMapping("/self/{id}")
    @PreAuthorize("@userCheck.isSelf(#id, authentication.getName())")
    public EmployeeDetailsDto getSelfEmployee(@PathVariable Long id) {
        return employeeService.getSelfEmployee(id);
    }

    // ENDPOINT: api/employees/id
    // id is dynamic
    //returns a status code instead of automatically showing it in the HRIS
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto response =  employeeService.updateEmployee(id, employeeRequestDto);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT: api/employees/status/id
    // id is dynamic
    @PatchMapping("/status/{id}")
    public EmployeeResponseDto updateStatus(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.updateStatus(id, employeeRequestDto);
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
