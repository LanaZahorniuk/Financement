package project.financement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import project.financement.dto.AccountInfoDto;
import project.financement.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountInfoMapper {

    @Mapping(target = "accountId", source = "accountId")
    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "currency", source = "currency")
    AccountInfoDto toDto(Account account);
}
