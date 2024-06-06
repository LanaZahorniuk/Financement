package project.financement.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.DELETE)
@Operation(
        summary = "Delete expense",
        description = "Delete an existing expense",
        tags = {"EXPENSE"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the expense to be deleted",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string", format = "uuid")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Expense successfully deleted"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Expense not found",
                        content = @io.swagger.v3.oas.annotations.media.Content(
                                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
public @interface DeleteExpense {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}