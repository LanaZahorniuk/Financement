package project.financement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ExpenseDto {

    @NotNull(message = "Expense amount is required")
    private BigDecimal expenseAmount;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;

    @NotBlank(message = "Expense category name is required")
    private String expenseCategoryName;

    @NotNull(message = "Account ID is required")
    private UUID accountId;

    private String expenseTransactionDescription;
}
