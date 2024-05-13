package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable UUID id) {
        return expenseService.findExpenseById(id);
    }

    @PostMapping("/create-expense")
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @PutMapping("/update-expense/{id}")
    public Expense updateExpense(@PathVariable UUID id, @RequestBody Expense expenseDetails) {
        return expenseService.updateExpense(id, expenseDetails);
    }

    @DeleteMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable UUID id) {
        expenseService.deleteExpense(id);
        return "Deleted expense";
    }

    @GetMapping("/by-expense-category/{expenseCategoryId}")
    public List<Expense> getExpensesByCategory(@PathVariable UUID expenseCategoryId) {
        return expenseService.findByExpenseCategoryId(expenseCategoryId);
    }

    @GetMapping("/expense-by-date")
    public List<Expense> getExpensesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return expenseService.findExpensesByDate(date);
    }
}
