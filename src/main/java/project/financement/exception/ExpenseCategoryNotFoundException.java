package project.financement.exception;

import java.util.UUID;

public class ExpenseCategoryNotFoundException extends RuntimeException {
    public ExpenseCategoryNotFoundException(UUID id) {
        super("Expense Category not found with id: " + id);
    }

    public ExpenseCategoryNotFoundException(String message) {
        super(message);
    }
}
