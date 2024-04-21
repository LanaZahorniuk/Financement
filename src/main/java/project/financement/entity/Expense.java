package project.financement.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"expenseCategoryName", "account"})
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

    @Column(name = "expense_transaction_description")
    private String expenseTransactionDescription;


    @ManyToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategoryName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                ", expenseTransactionDescription='" + expenseTransactionDescription + '\'' +
                '}';
    }
}
