package project.financement.exception;

public class ExpenseCategoryAlreadyExistsException extends RuntimeException {
    public ExpenseCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
