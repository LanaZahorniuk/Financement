package project.financement.service;

import project.financement.dto.ExpenseCategoryDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseCategoryService {

    List<ExpenseCategoryDto> findAll();

    ExpenseCategoryDto findByExpenseCategoryName(String expenseCategoryName);

    ExpenseCategoryDto saveExpenseCategory(UUID userId, ExpenseCategoryDto expenseCategoryDto);

    void deleteExpenseCategoryByName(String expenseCategoryName);
}
