package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserAfterCreationDto> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/create-user")
    public UserAfterCreationDto createUser(@RequestBody UserCreateDto newUserDto) {
        newUserDto.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
        return userService.createUser(newUserDto);
    }

    @PutMapping("/update-Email/{id}/{newEmail}")
    public ResponseEntity<UserAfterCreationDto> updateUserEmail(@PathVariable("id") UUID id, @PathVariable("newEmail") String newEmail) {
        return ResponseEntity.ok(userService.updateUserEmail(id, newEmail));
    }

    @PutMapping("/update-Password/{id}/{newPassword}")
    public ResponseEntity<UserAfterCreationDto> updateUserPassword(@PathVariable("id") UUID id, @PathVariable("newPassword") String newPassword) {
        return ResponseEntity.ok(userService.updateUserPassword(id, newPassword));
    }

    @PutMapping("/update-PhoneNumber/{id}/{newPhoneNumber}")
    public ResponseEntity<UserAfterCreationDto> updateUserPhoneNumber(@PathVariable("id") UUID id, @PathVariable("newPhoneNumber") String newPhoneNumber) {
        return ResponseEntity.ok(userService.updateUserPhoneNumber(id, newPhoneNumber));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}