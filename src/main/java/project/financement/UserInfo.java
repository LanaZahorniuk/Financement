package project.financement;

import java.util.Set;
import java.util.UUID;

public class UserInfo {
    private UUID userInfoId;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private User user;
    private Set<Account> accounts;
    private Role role;
}
