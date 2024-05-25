package project.financement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
    @Mapping(target = "userInfo.username", source = "userInfo.username")
    @Mapping(target = "userInfo.email", source = "userInfo.email")
    @Mapping(target = "userInfo.password", source = "userInfo.password")
    @Mapping(target = "userInfo.phoneNumber", source = "userInfo.phoneNumber")

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userInfo.userInfoId", ignore = true)
    @Mapping(target = "userInfo.user", ignore = true)
    @Mapping(target = "userInfo.accounts", ignore = true)

    //@Mapping(target = "userInfo.role.roleName", constant = "FreeUser")
    User toEntity(UserCreateDto newUserDto);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userInfoAfterCreation.username", source = "userInfo.username")
    UserAfterCreationDto toDto(User userAfterCreation);

}
