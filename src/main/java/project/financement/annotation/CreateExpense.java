package project.financement.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.financement.dto.ExpenseDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.RejectedExecutionHandler;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create expense",
        description = "Create a new expense",
        tags = {"EXPENSE"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Details of the new expense to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExpenseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "New expense",
                                        value = """
                                                {
                                                    "expenseAmount": 1000.000,
                                                    "expenseDate": "2007-08-08",
                                                    "expenseCategoryName": "Groceries",
                                                    "accountId": "888a5b08-573c-4f83-96e8-319ec975a111",
                                                    "expenseTransactionDescription": "food for dinner"
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Expense successfully created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExpenseDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = RejectedExecutionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Expense category not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        }
)
public @interface CreateExpense {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
