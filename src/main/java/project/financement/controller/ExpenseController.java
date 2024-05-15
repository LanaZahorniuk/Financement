package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.entity.Expense;
import project.financement.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/all-expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable UUID id) {
        return ResponseEntity.ok(expenseService.findExpenseById(id));
    }

    @PostMapping("/create-expense")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable UUID id, @RequestBody Expense expenseDetails) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDetails));
    }

    @DeleteMapping("/delete-expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable UUID id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Deleted expense");
    }

    @GetMapping("/by-expense-category/{expenseCategoryId}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable UUID expenseCategoryId) {
        return ResponseEntity.ok(expenseService.findByExpenseCategoryId(expenseCategoryId));
    }

    @GetMapping("/expense-by-date")
    public ResponseEntity<List<Expense>> getExpensesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(expenseService.findExpensesByDate(date));
    }
}
