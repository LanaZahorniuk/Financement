package project.financement.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.financement.exception.*;


@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseCategoryNotFoundException.class)
    public ResponseEntity<Object> handleExpenseCategoryNotFoundException(ExpenseCategoryNotFoundException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<Object> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpenseCategoryDeletionException.class)
    public ResponseEntity<Object> handleExpenseCategoryDeletionException(ExpenseCategoryDeletionException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExpenseCategoryAlreadyExistsException.class)
    public ResponseEntity<Object> handleExpenseCategoryAlreadyExistsException(ExpenseCategoryAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
