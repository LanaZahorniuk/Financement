package project.financement.entity;

import project.financement.entity.enums.IncomeCategory;

import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.UUID;

public class Income {
    private UUID incomeId;
    private BigDecimal incomeAmount;
    private LocalDate incomeDate;
    public IncomeCategory incomeCategoryName;
    private String incomeTransactionDescription;
    private Account account;
}
