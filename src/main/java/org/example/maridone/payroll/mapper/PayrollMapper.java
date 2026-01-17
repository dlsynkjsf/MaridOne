package org.example.maridone.payroll.mapper;

import org.example.maridone.payroll.dispute.DisputeRequest;
import org.example.maridone.payroll.dto.DisputeResponseDto;
import org.example.maridone.payroll.itemcomponent.DeductionsLine;
import org.example.maridone.payroll.itemcomponent.EarningsLine;
import org.example.maridone.payroll.PayrollItem;
import org.example.maridone.payroll.dto.DeductionsDto;
import org.example.maridone.payroll.dto.EarningsDto;
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

    @Mapping(source = "earningsId", target = "earningsId")
    EarningsDto toEarningsDto(EarningsLine earningsLine);

    List<EarningsDto> toEarningsDtos(List<EarningsLine> earningsLines);

    @Mapping(source = "deductionsId", target = "deductionsId")
    DeductionsDto toDeductionsDto(DeductionsLine deductionsLine);

    List<DeductionsDto> toDeductionsDtos(List<DeductionsLine> deductionsLines);

    @Mapping(source = "disputeId", target = "id")
    @Mapping(source = "payrollItem.itemId", target = "itemId")
    DisputeResponseDto toResponseDto(DisputeRequest disputeRequest);

    List<DisputeResponseDto> toResponseDtos(List<DisputeRequest> disputeRequests);

}
