package project.financement.service;

import project.financement.dto.ExpenseCategoryDto;
import project.financement.dto.ExpenseDto;
import project.financement.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseService {

    Expense createExpense(ExpenseDto expenseDto, ExpenseCategoryDto categoryDto);

    List<Expense> getAllExpenses();

    Expense findExpenseById(UUID id);

    ExpenseDto updateExpense(UUID id, ExpenseDto expenseDto);

    void deleteExpense(UUID id);

    List<Expense> findByExpenseCategoryId(UUID expenseCategoryId);

    List<Expense> findExpensesByDate(LocalDate date);

}
