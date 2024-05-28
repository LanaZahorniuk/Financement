package project.financement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.dto.ExpenseDto;
import project.financement.entity.Expense;
import project.financement.exception.ExpenseCategoryNotFoundException;
import project.financement.mapper.ExpenseMapper;
import project.financement.service.impl.ExpenseCategoryServiceImpl;
import project.financement.service.impl.ExpenseServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseServiceImpl expenseService;
    private final ExpenseCategoryServiceImpl expenseCategoryService;
    private final ExpenseMapper expenseMapper;

    @GetMapping("/all-expenses")
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        List<ExpenseDto> expenseDtos = expenseMapper.toDto(expenses);
        return ResponseEntity.ok(expenseDtos);
    }

    @PostMapping("/create-expense")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        try {
            ExpenseCategoryDto category = expenseCategoryService.findByExpenseCategoryName(expenseDto.getExpenseCategoryName());
            Expense newExpense = expenseService.createExpense(expenseDto, category);
            ExpenseDto newExpenseDto = expenseMapper.toDto(newExpense);
            return ResponseEntity.ok(newExpenseDto);
        } catch (ExpenseCategoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable String id, @RequestBody ExpenseDto expenseDetails) {
        ExpenseDto updatedExpenseDto = expenseService.updateExpense(UUID.fromString(id), expenseDetails);
        return ResponseEntity.ok(updatedExpenseDto);
    }

    @DeleteMapping("/delete-expense/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(UUID.fromString(id));
        return ResponseEntity.ok("Deleted expense");
    }

    @GetMapping("/by-expense-category/{expenseCategoryId}")
    public ResponseEntity<List<ExpenseDto>> getExpensesByCategory(@PathVariable String expenseCategoryId) {
        List<Expense> expenses = expenseService.findByExpenseCategoryId(UUID.fromString(expenseCategoryId));
        List<ExpenseDto> expenseDtos = expenseMapper.toDto(expenses);
        return ResponseEntity.ok(expenseDtos);
    }

    @GetMapping("/expense-by-date")
    public ResponseEntity<List<ExpenseDto>> getExpensesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Expense> expenses = expenseService.findExpensesByDate(date);
        List<ExpenseDto> expenseDtos = expenseMapper.toDto(expenses);
        return ResponseEntity.ok(expenseDtos);
    }
}
