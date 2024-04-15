package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@EqualsAndHashCode(exclude = {"dateOfBirth", "registrationDate", "userInfo"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "first_name ")
    private String firstName;

    @Column(name = "last_name ")
    private String lastName;

    @Column(name = "date_of_birth ")
    private LocalDate dateOfBirth;

    @Column(name = "registration_date ")
    private LocalDate registrationDate;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
