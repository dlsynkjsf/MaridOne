package org.example.maridone.leave;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.leave.balance.BalanceRequestDto;
import org.example.maridone.leave.balance.LeaveBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    @Mapping(target = "leaveId", ignore = true)
    @Mapping(source = "employee", target = "employee")
    LeaveBalance toEntity(BalanceRequestDto balanceRequestDto, Employee employee);
}
