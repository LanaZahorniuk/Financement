package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Account;
import project.financement.exception.AccountNotFoundException;
import project.financement.repository.AccountRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccount(UUID id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
    }

    @Transactional
    public Account createAccount(Account newAccount) {
        return accountRepository.save(newAccount);
    }

    @Transactional
    public Account updateAccountName(UUID id, String newAccountName) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        account.setAccountName(newAccountName);
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }

    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        accountRepository.delete(account);
    }

}
