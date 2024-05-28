package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.exception.ExpenseCategoryDeletionException;
import project.financement.service.ExpenseCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense-category")
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseCategoryDto>> getAllExpenseCategories() {
        List<ExpenseCategoryDto> expenseCategories = expenseCategoryService.findAll();
        return ResponseEntity.ok(expenseCategories);
    }

    @GetMapping("/{expenseCategoryName}")
    public ResponseEntity<ExpenseCategoryDto> getExpenseCategoryByName(@PathVariable String expenseCategoryName) {
        ExpenseCategoryDto expenseCategory = expenseCategoryService.findByExpenseCategoryName(expenseCategoryName);
        return ResponseEntity.ok(expenseCategory);
    }

    @PostMapping("/create-expense-category/{userId}")
    public ResponseEntity<ExpenseCategoryDto> createExpenseCategory(@PathVariable String userId, @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategoryDto newExpenseCategoryDto = expenseCategoryService.saveExpenseCategory(UUID.fromString(userId), expenseCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpenseCategoryDto);

    }

    @PutMapping("/update-expense-category/{expenseCategoryName}")
    public ResponseEntity<ExpenseCategoryDto> updateExpenseCategory(@PathVariable String expenseCategoryName, @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategoryDto updatedExpenseCategory = expenseCategoryService.updateExpenseCategory(expenseCategoryName, expenseCategoryDto);
        return ResponseEntity.ok(updatedExpenseCategory);
    }

    @DeleteMapping("/delete-expense-category/{expenseCategoryName}")
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
