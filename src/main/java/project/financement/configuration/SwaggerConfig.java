package project.financement.configuration;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Financement",
                description = "API documentation for a financial management application.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Lana Zahorniuk",
                        url = "https://github.com/LanaZahorniuk?tab=repositories"
                )
        )
)
public class SwaggerConfig {

    @Value("${swagger.swaggerPackage:project.financement}")
    private String swaggerPackage;

    public static final String ACCOUNT = "Account Service";
    public static final String EXPENSE_CATEGORY = "Expense Category Service";
    public static final String EXPENSE = "Expense Service";
    public static final String PAYMENT = "Payment Service";
    public static final String ROLE = "Role Service";
    public static final String USER = "User Service";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan(swaggerPackage)
                .addOpenApiCustomizer(openApi -> {
                    openApi.addTagsItem(new Tag().name(ACCOUNT).description("API for managing accounts"));
                    openApi.addTagsItem(new Tag().name(EXPENSE_CATEGORY).description("API for managing expense categories"));
                    openApi.addTagsItem(new Tag().name(EXPENSE).description("API for managing expenses"));
                    openApi.addTagsItem(new Tag().name(PAYMENT).description("API for managing payments"));
                    openApi.addTagsItem(new Tag().name(ROLE).description("API for managing roles"));
                    openApi.addTagsItem(new Tag().name(USER).description("API for managing users"));
                })
                .build();
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
//    }
}
