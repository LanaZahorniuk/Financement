package project.financement.security.utils;

public class AuthorizationRightsRoles {
    public static final String FREE_USER = "FreeUser";
    public static final String PREMIUM_USER = "PremiumUser";

    public static final String[] AUTH_LIST = {
            "/account/**",
            "/expense-category/**",
            "/expense/**",
            "/user/**"
    };

    public static final String[] SWAGGER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/configuration/**",
            "/configuration/ui",
            "/configuration/security",
            "/public",
            "/favicon.ico",
            "/h2-console/**"
    };
}
