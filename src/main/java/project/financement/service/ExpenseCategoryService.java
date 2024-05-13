package project.financement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.financement.entity.ExpenseCategory;
import project.financement.exception.ExpenseCategoryNotFoundException;
import project.financement.repository.ExpenseCategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public List<ExpenseCategory> findAll() {
        return expenseCategoryRepository.findAll();
    }

    public ExpenseCategory findExpenseCategoryById(UUID id) {
        return expenseCategoryRepository.findById(id).orElseThrow(() ->
                new ExpenseCategoryNotFoundException(id));
    }

    @Transactional
    public ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    @Transactional
    public void deleteExpenseCategoryById(UUID id) {
        expenseCategoryRepository.deleteById(id);
    }

    @Transactional
    public ExpenseCategory updateExpenseCategory(UUID id, ExpenseCategory expenseCategory) {
        ExpenseCategory expenseCategoryToUpdate = findExpenseCategoryById(id);
        expenseCategoryToUpdate.setExpenseCategoryName(expenseCategory.getExpenseCategoryName());
        return expenseCategoryRepository.save(expenseCategoryToUpdate);
    }
}
