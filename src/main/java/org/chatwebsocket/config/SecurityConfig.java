package org.chatwebsocket.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/**").authenticated()
                .requestMatchers("/static/js/**", "/webjars/**").permitAll()
            )
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .csrf().disable()
            .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            createUser("user1", "password", "GUEST"),
            createUser("user2", "password", "GUEST"),
            createUser("user3", "password", "GUEST")
        );
    }

    private UserDetails createUser(String name, String password, String role) {
        return User.withDefaultPasswordEncoder()
            .username(name)
            .password(password)
            .roles(role)
            .build();
    }
}
