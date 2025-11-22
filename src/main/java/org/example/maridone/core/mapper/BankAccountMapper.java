package org.example.maridone.core.mapper;

import org.example.maridone.core.bank.BankAccount;
import org.example.maridone.core.dto.BankAccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountDto bankAccounttoBankAccountDto(BankAccount bankAccount);

    BankAccount bankAccountDtotoBankAccount(BankAccountDto bankAccountDto);
}
