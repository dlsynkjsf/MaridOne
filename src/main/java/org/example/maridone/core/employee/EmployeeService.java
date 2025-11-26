package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeRequest;
import org.example.maridone.core.dto.EmployeeResponse;
import org.example.maridone.core.mapper.EmployeeMapper;
import org.example.maridone.enums.EmploymentStatus;
import org.example.maridone.enums.Position;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        //security
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequest);
        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeResponse(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.employeesToEmployeeResponses(employeeRepository.findAll());
    }

    public EmployeeResponse getEmployee(Long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.employeeToEmployeeResponse(emp);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest updated) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        //security

        emp.setFirstName(updated.getFirstName());
        emp.setMiddleName(updated.getMiddleName());
        emp.setLastName(updated.getLastName());
        emp.setBirthDate(updated.getBirthDate());
        emp.setEmploymentStatus(updated.getEmploymentStatus());
        emp.setEmail(updated.getEmail());
        emp.setPhoneNumber(updated.getPhoneNumber());
        emp.setPosition(updated.getPosition());
        emp.setAddress(updated.getAddress());

        employeeRepository.save(emp);

        return employeeMapper.employeeToEmployeeResponse(emp);
    }

    public EmployeeResponse updateStatus(Long id, EmployeeRequest updated) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        //security

        emp.setEmploymentStatus(updated.getEmploymentStatus());
        employeeRepository.save(emp);
        return employeeMapper.employeeToEmployeeResponse(emp);
    }

}
