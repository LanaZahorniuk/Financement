package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.financement.entity.enums.IncomeCategory;

import java.time.LocalDate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"account"})
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

    @Enumerated(EnumType.STRING)
    @Column(name = "income_category_name")
    public IncomeCategory incomeCategoryName;

    @Column(name = "income_transaction_description")
    private String incomeTransactionDescription;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", incomeAmount=" + incomeAmount +
                ", incomeDate=" + incomeDate +
                ", incomeCategoryName=" + incomeCategoryName +
                ", incomeTransactionDescription='" + incomeTransactionDescription + '\'' +
                '}';
    }
}
