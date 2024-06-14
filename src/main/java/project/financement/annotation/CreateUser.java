package project.financement.annotation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.financement.dto.UserAfterCreationDto;
import project.financement.dto.UserCreateDto;
import project.financement.handler.ResponseExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create a new user",
        description = "Create a new user account with the provided details",
        tags = {"USER"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Details of the new user",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UserCreateDto.class),
                        examples = {
                                @io.swagger.v3.oas.annotations.media.ExampleObject(
                                        name = "Example request with new user details",
                                        value = """
                                                {
                                                   "firstName": "Thomas",
                                                   "lastName": "Lin",
                                                   "dateOfBirth": "1971-12-01",
                                                   "userInfo": {
                                                     "username": "tomtom",
                                                     "email": "lin.t@example.com",
                                                     "password": "password123",
                                                     "phoneNumber": "7771167890"
                                                   }
                                                 }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "User created successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserAfterCreationDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid request data",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        }
)
public @interface CreateUser {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
