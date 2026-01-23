package org.example.maridone.core.employee;

import org.example.maridone.core.dto.EmployeeDetailsDto;
import org.example.maridone.core.dto.EmployeeRequestDto;
import org.example.maridone.core.dto.EmployeeResponseDto;
import org.example.maridone.core.mapper.CoreMapper;
import org.example.maridone.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CoreMapper coreMapper;

    EmployeeService(EmployeeRepository employeeRepository, CoreMapper coreMapper) {
        this.employeeRepository = employeeRepository;
        this.coreMapper = coreMapper;
    }


    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        //security
        Employee employee = coreMapper.employeeRequestToEmployee(employeeRequestDto);
        employeeRepository.save(employee);
        return coreMapper.employeeToEmployeeResponse(employee);
    }

    public List<EmployeeResponseDto> getAllEmployees() {
        return coreMapper.employeesToEmployeeResponses(employeeRepository.findAll());
    }

    public EmployeeResponseDto getEmployee(Long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return coreMapper.employeeToEmployeeResponse(emp);
    }

    public EmployeeDetailsDto getSelfEmployee(Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return coreMapper.employeeToEmployeeDetailsDto(emp);
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

        return coreMapper.employeeToEmployeeResponse(emp);
    }

    public EmployeeResponseDto updateStatus(Long id, EmployeeRequestDto updated) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        //security

        emp.setEmploymentStatus(updated.getEmploymentStatus());
        employeeRepository.save(emp);
        return coreMapper.employeeToEmployeeResponse(emp);
    }

}
