package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.dto.AccountDto;
import project.financement.mapper.AccountMapper;
import project.financement.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtoList = accountService.findAll();
        return new ResponseEntity<>(accountDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") UUID id) {
        AccountDto accountDto = accountService.getAccount(id);
        return ResponseEntity.ok(accountDto);

    }

    @PostMapping("/create-account")
    //@PreAuthorize ("hasRole('PremiumUser')")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto newAccountDto) {
        AccountDto accountDto = accountService.createAccount(newAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @PatchMapping("/update-accountName/{id}/{newAccountName}")
    public ResponseEntity<AccountDto> updateAccountName(@PathVariable("id") UUID id, @PathVariable("newAccountName") String newAccountName) {
        AccountDto updatedAccount = accountService.updateAccountName(id, newAccountName);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Deleted account.");
    }
}

