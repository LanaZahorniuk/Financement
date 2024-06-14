package project.financement.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import project.financement.generator.UuidTimeSequenceGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
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

