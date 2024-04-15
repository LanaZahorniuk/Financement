package project.financement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Budget {
    private UUID budgetId;
    private Account account;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal plannedIncome;
    private BigDecimal plannedExpenses;
}
