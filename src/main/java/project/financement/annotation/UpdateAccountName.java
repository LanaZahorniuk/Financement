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
import project.financement.dto.AccountDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.PATCH)
@Operation(
        summary = "Update account name",
        description = "Update the name of an account by its unique identifier",
        tags = {"ACCOUNT"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the account",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with valid accountId",
                                        value = "777a4b09-473c-4f83-96e8-319ec975a222"
                                )
                        }
                ),
                @Parameter(
                        name = "newAccountName",
                        description = "The new name for the account",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "New account name",
                                        value = "Pocket money"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Account name successfully updated",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = AccountDto.class)
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
                        description = "Account not found",
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
public @interface UpdateAccountName {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
