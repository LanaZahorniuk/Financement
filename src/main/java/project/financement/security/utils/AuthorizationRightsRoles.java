package project.financement.security.utils;

public class AuthorizationRightsRoles {
    public static final String FREE_USER = "FreeUser";
    public static final String PREMIUM_USER = "PremiumUser";

    public static final String[] FREE_USER_LIST = {
            "/account/**",
            "/expense/**",
            "/payments/**",
            "/expense-category/**",
            "/user/**",
    };

    public static final String[] PREMIUM_USER_LIST = {
            "/account/**",
            "/expense/**",
            "/expense-category/**",
            "/user/**",
    };
}
