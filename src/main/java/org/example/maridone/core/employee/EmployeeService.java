package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeDetailsDto;
import org.example.maridone.core.dto.EmployeeRequestDto;
import org.example.maridone.core.dto.EmployeeResponseDto;
import org.example.maridone.core.mapper.EmployeeMapper;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        //security
        Employee employee = employeeMapper.employeeRequestToEmployee(employeeRequestDto);
        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeResponse(employee);
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeMapper.employeesToEmployeeResponses(employeeRepository.findAll());
    }

    public EmployeeResponseDto getEmployee(Long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.employeeToEmployeeResponse(emp);
    }

    public EmployeeDetailsDto getSelfEmployee(Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return employeeMapper.employeeToEmployeeDetailsDto(emp);
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto updated) {
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

    public EmployeeResponseDto updateStatus(Long id, EmployeeRequestDto updated) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        //security

        emp.setEmploymentStatus(updated.getEmploymentStatus());
        employeeRepository.save(emp);
        return employeeMapper.employeeToEmployeeResponse(emp);
    }

}
