package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"incomeCategoryName", "account"})
@Table(name = "income")
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "income_id")
    private UUID incomeId;

    @Column(name = "income_amount")
    private BigDecimal incomeAmount;

    @Column(name = "income_date")
    private LocalDate incomeDate;

    @Column(name = "income_transaction_description")
    private String incomeTransactionDescription;


    @ManyToOne
    @JoinColumn(name = "income_category_id")
    private IncomeCategory incomeCategoryName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", incomeAmount=" + incomeAmount +
                ", incomeDate=" + incomeDate +
                ", incomeTransactionDescription='" + incomeTransactionDescription + '\'' +
                '}';
    }
}
