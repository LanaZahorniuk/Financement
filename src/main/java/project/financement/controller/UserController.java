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


    @PostMapping("/createU")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PostMapping("/updateUE/{id}/{newEmail}")
    public User updateUserEmail(@PathVariable("id") UUID id, @PathVariable("newEmail") String newEmail) {
        return userService.updateUserEmail(id, newEmail);
    }

    @PostMapping("/updateUP/{id}/{newPassword}")
    public User updateUserPassword(@PathVariable("id") UUID id, @PathVariable("newPassword") String newPassword) {
        return userService.updateUserPassword(id, newPassword);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        return userService.deleteUser(id);
    }
}