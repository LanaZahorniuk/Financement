package project.financement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import project.financement.dto.ExpenseCategoryDto;
import project.financement.entity.ExpenseCategory;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpenseCategoryMapper {

    @Mapping(target = "expenseCategoryId", ignore = true)
    @Mapping(target = "expenseCategoryName", source = "expenseCategoryName")
    @Mapping(target = "expenses", ignore = true)
    ExpenseCategory toEntity(ExpenseCategoryDto expenseCategory);

    @Mapping(target = "expenseCategoryName", source = "expenseCategoryName")
    ExpenseCategoryDto toDto(ExpenseCategory expenseCategory);

    List<ExpenseCategoryDto> toDto(List<ExpenseCategory> expenseCategories);
}
