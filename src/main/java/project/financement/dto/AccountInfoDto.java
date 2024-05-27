package project.financement.dto;


import lombok.Data;
import project.financement.entity.enums.Currency;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountInfoDto {

    private UUID accountId;

    private String accountName;

    private BigDecimal balance;

    private Currency currency;
}
