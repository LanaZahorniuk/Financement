package project.financement.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.financement.security.UserDetailsServiceImpl;
import project.financement.security.utils.AuthorizationRightsRoles;
import project.financement.security.utils.MyAccessDeniedHandler;

import static project.financement.security.utils.AuthorizationRightsRoles.FREE_USER;
import static project.financement.security.utils.AuthorizationRightsRoles.PREMIUM_USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/create-user").anonymous()
                        .requestMatchers(AuthorizationRightsRoles.FREE_USER_LIST).hasRole(FREE_USER)
                        .requestMatchers(AuthorizationRightsRoles.PREMIUM_USER_LIST).hasRole(PREMIUM_USER)
                        .requestMatchers("/expense-category/delete-expense-category/**").hasRole(PREMIUM_USER)
                        .requestMatchers("/expense-category/update-expense-category/**").hasRole(PREMIUM_USER)
                        .anyRequest().authenticated())
                .headers(headers -> headers.cacheControl(Customizer.withDefaults()).disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(myAccessDeniedHandler));
        return http.build();

    }
}
