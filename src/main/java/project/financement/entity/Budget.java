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
@Table(name = "budget")
@EqualsAndHashCode(exclude = {"startDate", "endDate", "plannedIncome", "plannedExpenses"})
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "budget_id")
    private UUID budgetId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "planned_income")
    private BigDecimal plannedIncome;

    @Column(name = "planned_expenses")
    private BigDecimal plannedExpenses;

    @OneToOne(mappedBy = "budget")
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", account=" + account +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", plannedIncome=" + plannedIncome +
                ", plannedExpenses=" + plannedExpenses +
                '}';
    }
}
