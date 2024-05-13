package project.financement.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.Expense;
import project.financement.exception.ExpenseNotFoundException;
import project.financement.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Transactional
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense findExpenseById(UUID id) {
        return expenseRepository.findById(id).orElseThrow(() ->
                new ExpenseNotFoundException(id));
    }

    @Transactional
    public Expense updateExpense(UUID id, Expense expense) {
        Expense expenseToUpdate = findExpenseById(id);
        expenseToUpdate.setExpenseAmount(expense.getExpenseAmount());
        expenseToUpdate.setExpenseDate(expense.getExpenseDate());
        expenseToUpdate.setExpenseTransactionDescription(expense.getExpenseTransactionDescription());
        return expenseRepository.save(expense);
    }

    @Transactional
    public void deleteExpense(UUID id) {
        Expense expense = findExpenseById(id);
        expenseRepository.delete(expense);
    }

    public List<Expense> findByExpenseCategoryId(UUID expenseCategoryId) {
        return expenseRepository.findByExpenseCategoryId(expenseCategoryId);
    }

    public List<Expense> findExpensesByDate(LocalDate date) {
        return expenseRepository.findByExpenseDate(date);
    }
}
