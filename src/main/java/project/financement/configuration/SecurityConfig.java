package project.financement.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.financement.security.UserDetailsServiceImpl;
import project.financement.security.utils.FinancementAccessDeniedHandler;

import static project.financement.security.utils.AuthorizationRightsRoles.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final FinancementAccessDeniedHandler accessDeniedHandler;

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
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout"))
                .authorizeHttpRequests(req -> req
                        .requestMatchers(SWAGGER_LIST).permitAll()
                        .requestMatchers("/user/create-user").anonymous()
                        .requestMatchers("/payments/**").hasRole(FREE_USER)
                        .requestMatchers(AUTH_LIST).hasAnyRole(FREE_USER, PREMIUM_USER)
                        .anyRequest().authenticated())
                .headers(headers -> headers.cacheControl(Customizer.withDefaults()).disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler));
        return http.build();

    }
}
