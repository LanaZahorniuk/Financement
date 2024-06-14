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
@Table(name = "expense_category")
@NoArgsConstructor
public class ExpenseCategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "expense_category_id")
    private UUID expenseCategoryId;

    @Column(name = "expense_category_name")
    private String expenseCategoryName;


    @OneToMany(mappedBy = "expenseCategoryName")
    private Set<Expense> expenses;

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "expenseCategoryId=" + expenseCategoryId +
                ", expenseCategoryName='" + expenseCategoryName + '\'' +
                '}';
    }
}
