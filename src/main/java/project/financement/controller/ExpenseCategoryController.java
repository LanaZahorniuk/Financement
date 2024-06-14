package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.financement.annotation.CreateExpenseCategory;
import project.financement.annotation.DeleteExpenseCategory;
import project.financement.annotation.GetAllExpenseCategories;
import project.financement.annotation.GetExpenseCategoryByName;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.exception.ExpenseCategoryDeletionException;
import project.financement.service.impl.ExpenseCategoryServiceImpl;

import java.util.List;
import java.util.UUID;

/**
 * Controller class handling HTTP requests related to expense categories.
 * Contains endpoints for managing expense categories including creation, retrieval by name,
 * deletion by name, and retrieval of all existing categories.
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/expense-category")
public class ExpenseCategoryController {

    private final ExpenseCategoryServiceImpl expenseCategoryService;

    @GetAllExpenseCategories(path = "/all")
    public ResponseEntity<List<ExpenseCategoryDto>> getAllExpenseCategories() {
        List<ExpenseCategoryDto> expenseCategories = expenseCategoryService.findAll();
        return ResponseEntity.ok(expenseCategories);
    }

    @CreateExpenseCategory(path = "/create-expense-category/{userId}")
    public ResponseEntity<ExpenseCategoryDto> createExpenseCategory(@PathVariable String userId, @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategoryDto newExpenseCategoryDto = expenseCategoryService.saveExpenseCategory(UUID.fromString(userId), expenseCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpenseCategoryDto);
    }

    @GetExpenseCategoryByName(path = "/{expenseCategoryName}")
    public ResponseEntity<ExpenseCategoryDto> getExpenseCategoryByName(@PathVariable String expenseCategoryName) {
        ExpenseCategoryDto expenseCategory = expenseCategoryService.findByExpenseCategoryName(expenseCategoryName);
        return ResponseEntity.ok(expenseCategory);
    }

    @DeleteExpenseCategory(path = "/delete-expense-category/{expenseCategoryName}")
    public ResponseEntity<String> deleteExpenseCategory(@PathVariable String expenseCategoryName) {
        try {
            expenseCategoryService.deleteExpenseCategoryByName(expenseCategoryName);
            return ResponseEntity.ok("Deleted expense category.");
        } catch (ExpenseCategoryDeletionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete expense category because it is referenced by one or more expenses.");
        }
    }
}
