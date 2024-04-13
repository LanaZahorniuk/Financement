package project.financement;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private UUID expenseId;
    private BigDecimal expenseAmount;
    private LocalDate expenseDate;
    private ExpenseCategory expenseCategoryName;
    private String expenseTransactionDescription;
    private Account account;
}
