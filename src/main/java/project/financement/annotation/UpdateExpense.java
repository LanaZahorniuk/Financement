package project.financement.annotation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.financement.dto.ExpenseDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.PUT)
@Operation(
        summary = "Update expense",
        description = "Update an existing expense",
        tags = {"EXPENSE"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the expense to be updated",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with valid expenseId",
                                        value = "333a9d13-833c-4f83-96e8-319ec975a666"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Updated details of the expense",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExpenseDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "Example of a request for adjusting expense transaction data",
                                        value = """
                                                {
                                                    "expenseAmount": 152.000,
                                                    "expenseDate": "2023-01-15",
                                                    "expenseCategoryName": "Groceries",
                                                    "accountId": "888a5b08-573c-4f83-96e8-319ec975a111",
                                                    "expenseTransactionDescription": "barbecue products"
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Expense successfully updated",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExpenseDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Expense not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface UpdateExpense {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
