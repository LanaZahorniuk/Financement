package project.financement.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private UUID expenseId;
    private BigDecimal expenseAmount;
    private LocalDate expenseDate;
    public ExpenseCategory expenseCategoryName;
    private String expenseTransactionDescription;
    private Account account;
}
