package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.AccountDto;
import project.financement.dto.AccountInfoDto;
import project.financement.entity.Account;
import project.financement.entity.User;
import project.financement.exception.AccountNotFoundException;
import project.financement.exception.UserNotFoundException;
import project.financement.mapper.AccountInfoMapper;
import project.financement.mapper.AccountMapper;
import project.financement.repository.AccountRepository;
import project.financement.repository.UserRepository;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final AccountInfoMapper accountInfoMapper;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<AccountInfoDto> findAllAccountsByUserId(UUID userId) {
        List<Account> accounts = accountRepository.findByUserInfo_User_UserId(userId);
        return accounts.stream()
                .map(accountInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public AccountDto createAccount(UUID userId, AccountDto newAccountDto) {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        checkAccountLimit(user);

        Account newAccount = accountMapper.toEntity(newAccountDto);
        newAccount.setUserInfo(user.getUserInfo());
        Account account = accountRepository.save(newAccount);
        return accountMapper.toDto(account);
    }

    private void checkAccountLimit(User user) {
        if (user.getUserInfo().getRole().getRoleName().equals("FreeUser") && user.getUserInfo().getAccounts().size() >= 2) {
            throw new RuntimeException("Free users can only have up to 2 accounts.");
        }
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AccountDto updateAccountName(UUID id, String newAccountName) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        account.setAccountName(newAccountName);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toDto(updatedAccount);
    }

    @Transactional
    public void deleteAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException(id));
        accountRepository.delete(account);
    }

}
