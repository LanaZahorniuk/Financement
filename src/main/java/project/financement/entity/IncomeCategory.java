package project.financement.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "income_category")
@NoArgsConstructor
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "income_category_id")
    private UUID incomeCategoryId;

    @Column(name = "income_category_name")
    private String incomeCategoryName;


    @OneToMany(mappedBy = "incomeCategoryName")
    private Set<Income> incomes;

    @Override
    public String toString() {
        return "IncomeCategory{" +
                "incomeCategoryId=" + incomeCategoryId +
                ", incomeCategoryName='" + incomeCategoryName + '\'' +
                '}';
    }
}

