package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Account;
import project.financement.exception.AccountNotFoundException;
import project.financement.repository.AccountRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public Account getAccount(UUID id) {
        logger.info("Fetching account with ID {}", id);
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
    }

    @Transactional
    public Account createAccount(Account newAccount) {
        logger.info("Creating a new account with account name {}", newAccount.getAccountName());
        return accountRepository.save(newAccount);
    }

    @Transactional
    public Account updateAccountName(UUID id, String newAccountName) {
        logger.info("Updating account with account name {}", newAccountName);
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        account.setAccountName(newAccountName);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Updated account ID {} with new name {}", id, updatedAccount.getAccountName());
        return updatedAccount;
    }

    public void deleteAccount(UUID id) {
        logger.info("Deleting account with account name {}", id);
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        accountRepository.delete(account);
        logger.info("Deleted account with account id {}", id);
    }

}
