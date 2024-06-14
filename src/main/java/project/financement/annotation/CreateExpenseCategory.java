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
import project.financement.dto.ExpenseCategoryDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create a new expense category",
        description = "Create a new expense category for a user by their unique identifier",
        tags = {"EXPENSE_CATEGORY"},
        parameters = {
                @Parameter(
                        name = "userId",
                        description = "The unique identifier of the user",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with valid userId",
                                        value = "12345678-1234-5678-1234-567812345678"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Details of the new expense category to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExpenseCategoryDto.class),
                        examples = {
                                @ExampleObject(
                                        name = "expense category",
                                        value = """
                                                {
                                                  "expenseCategoryName": "Vacation"
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Expense category successfully created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ExpenseCategoryDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "User not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "Expense category name already exists",
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
public @interface CreateExpenseCategory {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
