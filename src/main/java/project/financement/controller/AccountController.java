package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.dto.AccountDto;
import project.financement.dto.AccountInfoDto;
import project.financement.service.impl.AccountServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<AccountInfoDto>> getAllUsersAccounts(@PathVariable String userId) {
        List<AccountInfoDto> AccountInfoDtoList = accountService.findAllAccountsByUserId(UUID.fromString(userId));
        return new ResponseEntity<>(AccountInfoDtoList, HttpStatus.OK);
    }

    @PostMapping("/create-account/{userId}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String userId, @RequestBody AccountDto newAccountDto) {
        AccountDto accountDto = accountService.createAccount(UUID.fromString(userId), newAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PatchMapping("/update-accountName/{id}/{newAccountName}")
    public ResponseEntity<AccountDto> updateAccountName(@PathVariable("id") String id, @PathVariable("newAccountName") String newAccountName) {
        AccountDto updatedAccount = accountService.updateAccountName(UUID.fromString(id), newAccountName);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(UUID.fromString(id));
        return ResponseEntity.ok("Deleted account.");
    }
}

