package org.example.maridone.leave;

import org.example.maridone.core.employee.Employee;
import org.example.maridone.leave.dto.BalanceRequestDto;
import org.example.maridone.leave.dto.BalanceResponseDto;
import org.example.maridone.leave.balance.LeaveBalance;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    @Mapping(target = "leaveId", ignore = true)
    @Mapping(source = "employee", target = "employee")
    LeaveBalance toEntity(BalanceRequestDto balanceRequestDto, Employee employee);


    BalanceResponseDto toBalanceResponseDto(LeaveBalance leaveBalance);

    List<BalanceResponseDto> toBalanceResponsesDto(List<LeaveBalance> leaveBalances);

}
