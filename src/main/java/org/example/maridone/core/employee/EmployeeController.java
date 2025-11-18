package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeRequest;
import org.example.maridone.core.dto.EmployeeResponse;
import org.example.maridone.enums.EmploymentStatus;
import org.example.maridone.enums.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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





    /*
        FOR EMPLOYEE ROLE
     */



}
