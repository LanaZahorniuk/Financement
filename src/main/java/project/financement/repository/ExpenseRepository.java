package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.financement.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    @Query("SELECT e FROM Expense e WHERE e.expenseCategoryName.expenseCategoryId = :categoryId")
    List<Expense> findByExpenseCategoryNameId(@Param("categoryId") UUID expenseCategoryId);

    List<Expense> findByExpenseDate(LocalDate date);
}

