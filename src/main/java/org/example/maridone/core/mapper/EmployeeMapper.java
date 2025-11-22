package org.example.maridone.core.mapper;

import org.example.maridone.core.dto.EmployeeRequest;
import org.example.maridone.core.dto.EmployeeResponse;
import org.example.maridone.core.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "employeeId", target = "id")
    EmployeeResponse employeeToEmployeeResponse(Employee employee);

    @Mapping(source = "employeeId", target = "id")
    List<EmployeeResponse> employeesToEmployeeResponses(List<Employee> employees);

    Employee employeeRequestToEmployee(EmployeeRequest employeeRequest);

}
