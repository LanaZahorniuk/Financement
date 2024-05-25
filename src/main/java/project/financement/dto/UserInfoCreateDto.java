package project.financement.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;


@Getter
@Setter
@Value
public class UserInfoCreateDto {
    @NotBlank(message = "Username is required")
    String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    String password;

    String phoneNumber;

}
