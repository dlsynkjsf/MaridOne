package org.example.maridone.core.mapper;

import org.example.maridone.core.bank.BankAccount;
import org.example.maridone.core.dto.BankAccountDto;
import org.example.maridone.core.dto.EmployeeDetailsDto;
import org.example.maridone.core.dto.EmployeeRequestDto;
import org.example.maridone.core.dto.EmployeeResponseDto;
import org.example.maridone.core.employee.Employee;
import org.example.maridone.core.user.UserAccount;
import org.example.maridone.core.user.UserAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoreMapper {

    @Mapping(source = "employeeId", target = "id")
    EmployeeResponseDto employeeToEmployeeResponse(Employee employee);

    @Mapping(source = "employeeId", target = "id")
    EmployeeDetailsDto employeeToEmployeeDetailsDto(Employee employee);

    @Mapping(source = "employeeId", target = "id")
    List<EmployeeResponseDto> employeesToEmployeeResponses(List<Employee> employees);

    Employee employeeRequestToEmployee(EmployeeRequestDto employeeRequestDto);

    BankAccountDto bankAccounttoBankAccountDto(BankAccount bankAccount);

    BankAccount bankAccountDtotoBankAccount(BankAccountDto bankAccountDto);

    UserAccountDto userAccounttoUserAccountDto(UserAccount userAccount);

    UserAccount userAccountDtoToUserAccount(UserAccountDto userAccountDto);

}
