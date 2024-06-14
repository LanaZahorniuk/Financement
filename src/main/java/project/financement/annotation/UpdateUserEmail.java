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
import project.financement.dto.UserAfterCreationDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.PUT)
@Operation(
        summary = "Update User Email",
        description = "Update the email address of the user with the given ID",
        tags = {"USER"},
        parameters = {
                @Parameter(
                        name = "id",
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
                ),
                @Parameter(
                        name = "newEmail",
                        description = "The new email address for the user",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Example email address",
                                        value = "new.email@example.com"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User email updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserAfterCreationDto.class)
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
                        responseCode = "400",
                        description = "Invalid request",
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
public @interface UpdateUserEmail {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
