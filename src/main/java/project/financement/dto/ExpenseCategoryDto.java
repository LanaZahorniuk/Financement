package project.financement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExpenseCategoryDto {

    @NotBlank(message = "Expense category name is required.")
    private String expenseCategoryName;

}
