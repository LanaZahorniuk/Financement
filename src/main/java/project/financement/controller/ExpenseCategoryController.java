package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.entity.ExpenseCategory;
import project.financement.service.ExpenseCategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense-category")
public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllExpenseCategories() {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();
        return ResponseEntity.ok(expenseCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategory> getExpenseCategoryById(@PathVariable UUID id) {
        ExpenseCategory expenseCategory = expenseCategoryService.findExpenseCategoryById(id);
        return ResponseEntity.ok(expenseCategory);
    }

    @PostMapping("/create-expense-category")
    public ResponseEntity<ExpenseCategory> createExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
        ExpenseCategory newExpenseCategory = expenseCategoryService.saveExpenseCategory(expenseCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpenseCategory);

    }

    @PutMapping("/update-expense-category/{id}")
    public ResponseEntity<ExpenseCategory> updateExpenseCategory(@PathVariable UUID id, @RequestBody ExpenseCategory expenseCategory) {
        ExpenseCategory updatedExpenseCategory = expenseCategoryService.updateExpenseCategory(id, expenseCategory);
        return ResponseEntity.ok(updatedExpenseCategory);
    }

    @DeleteMapping("/delete-expense-category/{id}")
    public ResponseEntity<Void> deleteExpenseCategory(@PathVariable UUID id) {
        expenseCategoryService.deleteExpenseCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
