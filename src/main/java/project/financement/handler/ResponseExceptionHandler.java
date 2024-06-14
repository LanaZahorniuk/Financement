package project.financement.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.financement.exception.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "User not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Account not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseCategoryNotFoundException.class)
    public ResponseEntity<Object> handleExpenseCategoryNotFoundException(ExpenseCategoryNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Expense Category not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Object> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Expense not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseCategoryDeletionException.class)
    public ResponseEntity<Object> handleExpenseCategoryDeletionException(ExpenseCategoryDeletionException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Expense Category Deletion Error");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExpenseCategoryAlreadyExistsException.class)
    public ResponseEntity<Object> handleExpenseCategoryAlreadyExistsException(ExpenseCategoryAlreadyExistsException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Expense category already exists");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatusCode status,
            @NotNull WebRequest request) {

        Map<String, String> body = new HashMap<>();
        body.put("error", "Validation Error");
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                body.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
