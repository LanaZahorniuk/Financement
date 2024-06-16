package project.financement.security.utils;

public class AuthorityRoleList {
    public static final String FreeUser = "FreeUser";
    public static final String PremiumUser = "PremiumUser";

    public static final String[] FREE_USER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/configuration/**",
            "/public/**",
            "/favicon.ico",
            "/h2-console/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",

            "/account/**",
            "/expense-category/**",
            "/expense/**",
            "/user/**",
            "/payments/**"
    };

    public static final String[] PREMIUM_USER_LIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/webjars/**",
            "/configuration/**",
            "/public/**",
            "/favicon.ico",
            "/h2-console/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",

            "/account/**",
            "/expense-category/**",
            "/expense/**",
            "/user/**"
    };
}
