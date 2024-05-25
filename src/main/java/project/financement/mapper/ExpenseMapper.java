package project.financement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import project.financement.dto.ExpenseDto;
import project.financement.entity.Expense;
import project.financement.entity.ExpenseCategory;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpenseMapper {

    @Mapping(target = "expenseAmount", source = "expenseAmount")
    @Mapping(target = "expenseDate", source = "expenseDate")
    @Mapping(target = "expenseTransactionDescription", source = "expenseTransactionDescription")
    @Mapping(target = "expenseCategoryName", source = "expenseCategoryName", qualifiedByName = "mapToExpenseCategory")
    @Mapping(target = "account.accountId", source = "accountId")

    @Mapping(target = "account.accountName", ignore = true)
    @Mapping(target = "account.balance", ignore = true)
    @Mapping(target = "account.currency", ignore = true)
    Expense toEntity(ExpenseDto expense);


    @Mapping(target = "expenseAmount", source = "expenseAmount")
    @Mapping(target = "expenseDate", source = "expenseDate")
    @Mapping(target = "expenseTransactionDescription", source = "expenseTransactionDescription")
    @Mapping(target = "expenseCategoryName", source = "expenseCategoryName", qualifiedByName = "mapToExpenseCategoryName")
    @Mapping(target = "accountId", source = "account.accountId")
    ExpenseDto toDto(Expense expense);

    List<ExpenseDto> toDto(List<Expense> expenses);

    @Named("mapToExpenseCategory")
    default ExpenseCategory mapToExpenseCategory(String expenseCategoryName) {
        ExpenseCategory category = new ExpenseCategory();
        category.setExpenseCategoryName(expenseCategoryName);
        return category;
    }

    @Named("mapToExpenseCategoryName")
    default String mapToExpenseCategoryName(ExpenseCategory expenseCategory) {
        return expenseCategory.getExpenseCategoryName();
    }

}
