package project.financement.controller;

import lombok.RequiredArgsConstructor;
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
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }


    @PostMapping("/create-user")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/update-Email/{id}/{newEmail}")
    public User updateUserEmail(@PathVariable("id") UUID id, @PathVariable("newEmail") String newEmail) {
        return userService.updateUserEmail(id, newEmail);
    }

    @PutMapping("/update-Password/{id}/{newPassword}")
    public User updateUserPassword(@PathVariable("id") UUID id, @PathVariable("newPassword") String newPassword) {
        return userService.updateUserPassword(id, newPassword);
    }

    @PutMapping("/update-PhoneNumber/{id}/{newPhoneNumber}")
    public User updateUserPhoneNumber(@PathVariable("id") UUID id, @PathVariable("newPhoneNumber") String newPhoneNumber) {
        return userService.updateUserPhoneNumber(id, newPhoneNumber);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        return userService.deleteUser(id);
    }
}