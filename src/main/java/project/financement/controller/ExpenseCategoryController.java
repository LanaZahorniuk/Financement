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

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategoryDto> getExpenseCategoryById(@PathVariable UUID id) {
        ExpenseCategoryDto expenseCategory = expenseCategoryService.findExpenseCategoryById(id);
        return ResponseEntity.ok(expenseCategory);
    }

    @PostMapping("/create-expense-category")
    public ResponseEntity<ExpenseCategoryDto> createExpenseCategory(@RequestBody ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategoryDto newExpenseCategoryDto = expenseCategoryService.saveExpenseCategory(expenseCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpenseCategoryDto);

    }

    @PutMapping("/update-expense-category/{id}")
    public ResponseEntity<ExpenseCategoryDto> updateExpenseCategory(@PathVariable UUID id, @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategoryDto updatedExpenseCategory = expenseCategoryService.updateExpenseCategory(id, expenseCategoryDto);
        return ResponseEntity.ok(updatedExpenseCategory);
    }

    @DeleteMapping("/delete-expense-category/{id}")
    public ResponseEntity<String> deleteExpenseCategory(@PathVariable UUID id) {
        try {
            expenseCategoryService.deleteExpenseCategoryById(id);
            return ResponseEntity.ok("Deleted expense category.");
        } catch (ExpenseCategoryDeletionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete expense category because it is referenced by one or more expenses.");
        }
    }
}
