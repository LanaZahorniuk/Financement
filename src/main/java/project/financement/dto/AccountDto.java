package project.financement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.financement.entity.enums.Currency;

import java.math.BigDecimal;

@Data
public class AccountDto {

    @NotBlank(message = "Account name is required.")
    private String accountName;

    @NotNull(message = "Balance is required.")
    private BigDecimal balance;

    @NotNull(message = "Currency is required.")
    private Currency currency;
}
