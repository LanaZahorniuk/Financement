package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.financement.entity.Account;
import project.financement.service.AccountService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    //public Account getAccount(@PathVariable("id") UUID id) {
    //return accountService.getAccount(id);
    public ResponseEntity<Account> getAccount(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(accountService.getAccount(id));

    }

    @PostMapping("/create-account")
    //@PreAuthorize ("hasRole('PremiumUser')")
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(newAccount));
    }

    @PatchMapping("/update-accountName/{id}/{newAccountName}")
    public ResponseEntity<Account> updateAccountName(@PathVariable("id") UUID id, @PathVariable("newAccountName") String newAccountName) {
        return ResponseEntity.ok(accountService.updateAccountName(id, newAccountName));
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully.");
    }
}
