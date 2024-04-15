package project.financement.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.financement.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "expense")
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "expense_id")
    private UUID expenseId;

    @Column(name = "expense_amount")
    private BigDecimal expenseAmount;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category_name")
    public ExpenseCategory expenseCategoryName;

    @Column(name = "expense_transaction_description")
    private String expenseTransactionDescription;

    @ManyToOne
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(expenseId, expense.expenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                ", expenseCategoryName=" + expenseCategoryName +
                ", expenseTransactionDescription='" + expenseTransactionDescription + '\'' +
                '}';
    }
}
