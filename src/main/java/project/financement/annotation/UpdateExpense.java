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
                        schema = @Schema(type = "string", format = "uuid")
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Updated details of the expense",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ExpenseDto.class)
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
                @SecurityRequirement(name = "bearerAuth")
        }
)
public @interface UpdateExpense {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
