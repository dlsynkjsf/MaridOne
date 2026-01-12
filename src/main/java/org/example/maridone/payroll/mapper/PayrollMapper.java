package org.example.maridone.payroll.mapper;

import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.dto.ItemDetailsDto;
import org.example.maridone.payroll.dto.RunDetailsDto;
import org.example.maridone.payroll.run.PayrollRun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayrollMapper {

    @Mapping(source = "payrollRun", target = "runDetails")
    @Mapping(source = "itemId", target = "id")
    ItemDetailsDto toItemDetailsDto(PayrollItem payrollItem);

    @Mapping(source = "payId", target = "id")
    RunDetailsDto toRunDetailsDto(PayrollRun payrollRun);

    List<ItemDetailsDto> toItemDetailsDtos(List<PayrollItem> payrollItems);
}
