package project.financement.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.entity.ExpenseCategory;
import project.financement.entity.User;
import project.financement.exception.ExpenseCategoryNotFoundException;
import project.financement.exception.UserNotFoundException;
import project.financement.mapper.ExpenseCategoryMapper;
import project.financement.repository.ExpenseCategoryRepository;
import project.financement.repository.UserRepository;
import project.financement.service.ExpenseCategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final UserRepository userRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    @Override
    @Transactional
    public List<ExpenseCategoryDto> findAll() {
        List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findAll();
        return expenseCategoryMapper.toDto(expenseCategories);
    }

    @Override
    @Transactional
    public ExpenseCategoryDto findByExpenseCategoryName(String expenseCategoryName) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseCategoryName)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException("Expense category not found"));
        return expenseCategoryMapper.toDto(expenseCategory);
    }

    @Override
    @Transactional
    public ExpenseCategoryDto saveExpenseCategory(UUID userId, ExpenseCategoryDto expenseCategoryDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getUserInfo().getRole().getRoleName().equals("FreeUser")) {
            throw new RuntimeException("FreeUser cannot create custom expense categories.");
        }

        ExpenseCategory expenseCategory = expenseCategoryMapper.toEntity(expenseCategoryDto);
        ExpenseCategory savedExpenseCategory = expenseCategoryRepository.save(expenseCategory);
        return expenseCategoryMapper.toDto(savedExpenseCategory);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ExpenseCategoryDto updateExpenseCategory(String expenseCategoryName, ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategoryToUpdate = expenseCategoryRepository.findByExpenseCategoryName(expenseCategoryName)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException(expenseCategoryName));
        expenseCategoryToUpdate.setExpenseCategoryName(expenseCategoryDto.getExpenseCategoryName());
        ExpenseCategory updatedExpenseCategory = expenseCategoryRepository.save(expenseCategoryToUpdate);
        return expenseCategoryMapper.toDto(updatedExpenseCategory);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteExpenseCategoryByName(String expenseCategoryName) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseCategoryName)
                .orElseThrow(() -> new ExpenseCategoryNotFoundException("Expense category with name " + expenseCategoryName + " not found."));
        List<String> standardCategories = Arrays.asList(
                "Groceries", "Utilities", "Transport", "Housing", "Health",
                "Personal Expenses", "Education", "Entertainment"
        );
        if (standardCategories.contains(expenseCategoryName)) {
            throw new IllegalArgumentException("Cannot delete standard expense category.");
        }
        expenseCategoryRepository.delete(expenseCategory);
    }
}
