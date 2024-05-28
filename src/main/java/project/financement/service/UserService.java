package project.financement.service;

import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;

import java.util.UUID;

public interface UserService {

    UserAfterCreationDto getUserById(UUID id);

    UserAfterCreationDto createUser(UserCreateDto newUserDto);

    UserAfterCreationDto updateUserEmail(UUID id, String newEmail);

    UserAfterCreationDto updateUserPassword(UUID id, String newPassword);

    UserAfterCreationDto updateUserPhoneNumber(UUID id, String newPhoneNumber);

    String deleteUser(UUID id);
}
