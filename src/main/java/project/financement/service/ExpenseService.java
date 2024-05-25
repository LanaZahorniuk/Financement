package project.financement.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.dto.ExpenseDto;
import project.financement.entity.Account;
import project.financement.entity.Expense;
import project.financement.entity.ExpenseCategory;
import project.financement.exception.AccountNotFoundException;
import project.financement.exception.ExpenseCategoryNotFoundException;
import project.financement.exception.ExpenseNotFoundException;
import project.financement.mapper.ExpenseMapper;
import project.financement.repository.AccountRepository;
import project.financement.repository.ExpenseCategoryRepository;
import project.financement.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final AccountRepository accountRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseMapper expenseMapper;

    @Transactional
    public Expense createExpense(ExpenseDto expenseDto, ExpenseCategoryDto categoryDto) {
        Account account = accountRepository.findById(expenseDto.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(expenseDto.getAccountId()));
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseDto.getExpenseCategoryName())
                .orElseThrow(() -> new ExpenseCategoryNotFoundException("Expense category not found"));
        Expense expense = expenseMapper.toEntity(expenseDto);
        expense.setExpenseId(UUID.randomUUID());
        expense.setExpenseCategoryName(expenseCategory);
        expense.setAccount(account);
        return expenseRepository.save(expense);
    }

    @Transactional
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense findExpenseById(UUID id) {
        return expenseRepository.findById(id).orElseThrow(() ->
                new ExpenseNotFoundException(id));
    }

    @Transactional
    public ExpenseDto updateExpense(UUID id, ExpenseDto expenseDto) {
        Expense expenseToUpdate = expenseRepository.findById(id).
                orElseThrow(() -> new ExpenseNotFoundException(id));
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseDto.getExpenseCategoryName())
                .orElseThrow(() -> new ExpenseCategoryNotFoundException("Expense category not found"));

        expenseToUpdate.setExpenseAmount(expenseDto.getExpenseAmount());
        expenseToUpdate.setExpenseDate(expenseDto.getExpenseDate());
        expenseToUpdate.setExpenseTransactionDescription(expenseDto.getExpenseTransactionDescription());
        expenseToUpdate.setExpenseCategoryName(expenseCategory);
        expenseToUpdate.setAccount(expenseToUpdate.getAccount());
        Expense updatedExpense = expenseRepository.save(expenseToUpdate);
        return expenseMapper.toDto(updatedExpense);
    }

    @Transactional
    public void deleteExpense(UUID id) {
        Expense expense = findExpenseById(id);
        expenseRepository.delete(expense);
    }


    public List<Expense> findByExpenseCategoryId(UUID expenseCategoryId) {
        return expenseRepository.findByExpenseCategoryNameId(expenseCategoryId);
    }

    public List<Expense> findExpensesByDate(LocalDate date) {
        return expenseRepository.findByExpenseDate(date);
    }
}
