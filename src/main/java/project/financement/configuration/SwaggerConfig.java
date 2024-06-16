package project.financement.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "Financement",
                description = "API documentation for a financial management application.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Lana Zahorniuk",
                        email = "lanazahorniuk@gmail.com",
                        url = "https://github.com/LanaZahorniuk?tab=repositories"
                )
        )
)

@Configuration
public class SwaggerConfig {

    @Value("${swagger.swaggerPackage:project.financement}")
    private String swaggerPackage;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan(swaggerPackage)
                .addOpenApiCustomiser(openApi -> {
                    openApi.addTagsItem(new Tag().name("Account Service").description("API for managing accounts"));
                    openApi.addTagsItem(new Tag().name("Expense Category Service").description("API for managing expense categories"));
                    openApi.addTagsItem(new Tag().name("Expense Service").description("API for managing expenses"));
                    openApi.addTagsItem(new Tag().name("Payment Service").description("API for managing payments"));
                    openApi.addTagsItem(new Tag().name("Role Service").description("API for managing roles"));
                    openApi.addTagsItem(new Tag().name("User Service").description("API for managing users"));
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(Type.HTTP)
                                .scheme("basic")));
    }
}
