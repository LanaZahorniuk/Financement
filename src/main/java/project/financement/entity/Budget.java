package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import project.financement.generator.UuidTimeSequenceGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "budget")
@EqualsAndHashCode(exclude = {"account"})
@NoArgsConstructor
public class Budget {
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
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
    private Account account;

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", plannedIncome=" + plannedIncome +
                ", plannedExpenses=" + plannedExpenses +
                '}';
    }
}


/*
@PreAuthorize("hasAuthority('CreateBudget')")
public void createBudget(Budget budget) {
}
 */