package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByExpenseCategoryId(UUID expenseCategoryId);

    List<Expense> findByExpenseDate(LocalDate date);
}

