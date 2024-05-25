package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.AccountDto;
import project.financement.entity.Account;
import project.financement.exception.AccountNotFoundException;
import project.financement.mapper.AccountMapper;
import project.financement.repository.AccountRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    @Transactional
    public List<AccountDto> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountDto getAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        return accountMapper.toDto(account);
    }

    @Transactional
    public AccountDto createAccount(AccountDto newAccountDto) {
        Account newAccount = accountMapper.toEntity(newAccountDto);
        Account account = accountRepository.save(newAccount);
        return accountMapper.toDto(account);
    }

    @Transactional
    public AccountDto updateAccountName(UUID id, String newAccountName) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        account.setAccountName(newAccountName);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toDto(updatedAccount);
    }

    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        accountRepository.delete(account);
    }

}
