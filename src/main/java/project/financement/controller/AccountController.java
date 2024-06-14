package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financement.annotation.CreateAccount;
import project.financement.annotation.DeleteAccount;
import project.financement.annotation.GetAllAccounts;
import project.financement.annotation.UpdateAccountName;
import project.financement.dto.AccountDto;
import project.financement.dto.AccountInfoDto;
import project.financement.service.impl.AccountServiceImpl;

import java.util.List;
import java.util.UUID;

/**
 * Controller class handling HTTP requests related to accounts.
 * Contains endpoints for retrieving all accounts of a user, creating a new account,
 * updating an account's name, and deleting an account.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    @GetAllAccounts(path = "/all/{userId}")
    public ResponseEntity<List<AccountInfoDto>> getAllUsersAccounts(@PathVariable String userId) {
        List<AccountInfoDto> AccountInfoDtoList = accountService.findAllAccountsByUserId(UUID.fromString(userId));
        return new ResponseEntity<>(AccountInfoDtoList, HttpStatus.OK);
    }

    @CreateAccount(path = "/create-account/{userId}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String userId, @RequestBody AccountDto newAccountDto) {
        AccountDto accountDto = accountService.createAccount(UUID.fromString(userId), newAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }

    @UpdateAccountName(path = "/update-accountName/{id}/{newAccountName}")
    public ResponseEntity<AccountDto> updateAccountName(@PathVariable("id") String id, @PathVariable("newAccountName") String newAccountName) {
        AccountDto updatedAccount = accountService.updateAccountName(UUID.fromString(id), newAccountName);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteAccount(path = "/delete-account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(UUID.fromString(id));
        return ResponseEntity.ok("Deleted account.");
    }
}

