package project.financement.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping(method = RequestMethod.GET)
@Operation(
        summary = "Get expenses by date",
        description = "Retrieve expenses by their associated date",
        tags = {"EXPENSE"},
        parameters = {
                @Parameter(
                        name = "date",
                        description = "The date of the expenses",
                        required = true,
                        in = ParameterIn.QUERY,
                        schema = @Schema(type = "string", format = "date"),
                        examples = {
                                @io.swagger.v3.oas.annotations.media.ExampleObject(
                                        name = "Example request with valid date",
                                        value = "2024-06-01"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Expenses retrieved successfully",
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
                @SecurityRequirement(name = "bearerAuth")
        }
)
public @interface GetExpensesByDate {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}