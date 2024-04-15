package project.financement.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID accountId;
    private String accountName;
    private BigDecimal balance;
    private Currency currency;
    private Budget budget;
}
