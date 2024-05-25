package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.entity.ExpenseCategory;
import project.financement.exception.ExpenseCategoryDeletionException;
import project.financement.exception.ExpenseCategoryNotFoundException;
import project.financement.mapper.ExpenseCategoryMapper;
import project.financement.repository.ExpenseCategoryRepository;
import project.financement.repository.ExpenseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Transactional
    public List<ExpenseCategoryDto> findAll() {
        List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findAll();
        return expenseCategoryMapper.toDto(expenseCategories);
    }

    @Transactional
    public ExpenseCategoryDto findExpenseCategoryById(UUID id) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findById(id).orElseThrow(() ->
                new ExpenseCategoryNotFoundException(id));
        return expenseCategoryMapper.toDto(expenseCategory);
    }

    @Transactional
    public ExpenseCategoryDto findByExpenseCategoryName(String expenseCategoryName) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseCategoryName)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException("Expense category not found"));
        return expenseCategoryMapper.toDto(expenseCategory);
    }

    @Transactional
    public ExpenseCategoryDto saveExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = expenseCategoryMapper.toEntity(expenseCategoryDto);
        ExpenseCategory savedExpenseCategory = expenseCategoryRepository.save(expenseCategory);
        return expenseCategoryMapper.toDto(savedExpenseCategory);
    }

    @Transactional
    public ExpenseCategoryDto updateExpenseCategory(UUID id, ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategoryToUpdate = expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException(id));
        expenseCategoryToUpdate.setExpenseCategoryName(expenseCategoryDto.getExpenseCategoryName());
        ExpenseCategory updatedExpenseCategory = expenseCategoryRepository.save(expenseCategoryToUpdate);
        return expenseCategoryMapper.toDto(updatedExpenseCategory);
    }

    @Transactional
    public void deleteExpenseCategoryById(UUID id) {
        if (expenseRepository.existsByExpenseCategoryName_ExpenseCategoryId(id)) {
            throw new ExpenseCategoryDeletionException("Cannot delete expense category because it is referenced by one or more expenses.");
        }
        expenseCategoryRepository.deleteById(id);
    }
}
