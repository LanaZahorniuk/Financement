package project.financement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.financement.entity.enums.Currency;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @NotBlank(message = "Account name is required.")
    private String accountName;

    @NotNull(message = "Balance is required.")
    private BigDecimal balance;

    @NotNull(message = "Currency is required.")
    private Currency currency;
}
