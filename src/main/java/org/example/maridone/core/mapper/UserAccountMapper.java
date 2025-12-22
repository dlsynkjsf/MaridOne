package org.example.maridone.core.mapper;

import org.example.maridone.core.user.UserAccountDto;
import org.example.maridone.core.user.UserAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountDto userAccounttoUserAccountDto(UserAccount userAccount);

    UserAccount userAccountDtoToUserAccount(UserAccountDto userAccountDto);
}
