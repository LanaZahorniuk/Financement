package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.annotation.*;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Controller class handling HTTP requests related to users.
 * Contains endpoints for retrieving user by ID, creating a new user,
 * updating user's email, password, and phone number, and deleting a user.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @GetUserById(path = "/{id}")
    public ResponseEntity<UserAfterCreationDto> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(UUID.fromString(id)));
    }

    @CreateUser(path = "/create-user")
    public UserAfterCreationDto createUser(@RequestBody UserCreateDto newUserDto) {
        newUserDto.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
        return userService.createUser(newUserDto);
    }

    @UpdateUserEmail(path = "/update-Email/{id}/{newEmail}")
    public ResponseEntity<UserAfterCreationDto> updateUserEmail(@PathVariable("id") String id, @PathVariable("newEmail") String newEmail) {
        return ResponseEntity.ok(userService.updateUserEmail(UUID.fromString(id), newEmail));
    }

    @UpdateUserPassword(path = "/update-Password/{id}/{newPassword}")
    public ResponseEntity<UserAfterCreationDto> updateUserPassword(@PathVariable("id") String id, @PathVariable("newPassword") String newPassword) {
        return ResponseEntity.ok(userService.updateUserPassword(UUID.fromString(id), newPassword));
    }

    @UpdateUserPhoneNumber(path = "/update-PhoneNumber/{id}/{newPhoneNumber}")
    public ResponseEntity<UserAfterCreationDto> updateUserPhoneNumber(@PathVariable("id") String id, @PathVariable("newPhoneNumber") String newPhoneNumber) {
        return ResponseEntity.ok(userService.updateUserPhoneNumber(UUID.fromString(id), newPhoneNumber));
    }

    @DeleteUser(path = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.deleteUser(UUID.fromString(id)));
    }
}