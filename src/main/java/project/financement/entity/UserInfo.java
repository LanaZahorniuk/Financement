package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name = "users_info")
@EqualsAndHashCode(exclude = {"user", "accounts", "role"})
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_info_id")
    private UUID userInfoId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email ")
    private String email;

    @Column(name = "password ")
    private String password;

    @Column(name = "phone_number ")
    private String phoneNumber;

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userInfo")
    @JoinColumn(name = "account_id")
    private Set<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
