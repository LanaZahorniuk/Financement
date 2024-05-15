package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.entity.User;
import project.financement.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @PutMapping("/update-Email/{id}/{newEmail}")
    public ResponseEntity<User> updateUserEmail(@PathVariable("id") UUID id, @PathVariable("newEmail") String newEmail) {
        return ResponseEntity.ok(userService.updateUserEmail(id, newEmail));
    }

    @PutMapping("/update-Password/{id}/{newPassword}")
    public ResponseEntity<User> updateUserPassword(@PathVariable("id") UUID id, @PathVariable("newPassword") String newPassword) {
        return ResponseEntity.ok(userService.updateUserPassword(id, newPassword));
    }

    @PutMapping("/update-PhoneNumber/{id}/{newPhoneNumber}")
    public ResponseEntity<User> updateUserPhoneNumber(@PathVariable("id") UUID id, @PathVariable("newPhoneNumber") String newPhoneNumber) {
        return ResponseEntity.ok(userService.updateUserPhoneNumber(id, newPhoneNumber));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}