package project.financement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.financement.entity.ExpenseCategory;

import java.util.Optional;
import java.util.UUID;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, UUID> {
    Optional<ExpenseCategory> findByExpenseCategoryName(String expenseCategoryName);
}
