package atln72.hope.Config;

import atln72.hope.service.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter; // Injection du filtre
    }

    // Configuration de la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/auth/**").permitAll() // URLs publiques
                        .anyRequest().authenticated() // Authentification nécessaire pour toutes les autres requêtes
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Ajout du filtre JWT
        return http.build();
    }

    // Bean pour le AuthenticationManager (facultatif, utile si nécessaire dans les services)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}