package project.financement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import project.financement.dto.AccountDto;
import project.financement.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "currency", source = "currency")

    @Mapping(target = "accountId", ignore = true)
    Account toEntity(AccountDto accountDto);

    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "currency", source = "currency")
    AccountDto toDto(Account account);
}
