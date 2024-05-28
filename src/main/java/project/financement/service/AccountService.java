package project.financement.service;

import project.financement.dto.AccountDto;
import project.financement.dto.AccountInfoDto;
import project.financement.entity.User;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    List<AccountInfoDto> findAllAccountsByUserId(UUID userId);

    AccountDto createAccount(UUID userId, AccountDto newAccountDto);

    void checkAccountLimit(User user);

    AccountDto updateAccountName(UUID id, String newAccountName);

    void deleteAccount(UUID id);

}
