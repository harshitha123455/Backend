package com.ibm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ibm.security.jwt.AuthTokenFilter;

/**
 * Configuration class for Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Creates a bean for password encoder.
     *
     * @return The password encoder.
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the HTTP security settings.
     *
     * @param http The HttpSecurity object to configure.
     * @throws Exception If an error occurs during configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().and()
            .authorizeRequests()
                .antMatchers("/admin/login").permitAll() // Permit access to /admin/login endpoint without authentication
                .antMatchers("/admin/add").permitAll() // Permit access to /admin/add endpoint without authentication
                .antMatchers("/admin/**").authenticated() // Require authentication for all endpoints under /admin
                .anyRequest().permitAll() // Permit access to all other endpoints without authentication
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint()) // Set the authentication entry point for unauthorized requests
            .and()
            .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class) // Add AuthTokenFilter before UsernamePasswordAuthenticationFilter in the filter chain
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Configure logout endpoint
                .permitAll(); // Permit access to the logout endpoint without authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // Configure user details service and password encoder
    }

    /**
     * Creates a bean for the authentication entry point.
     *
     * @return The authentication entry point.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Creates a bean for the authentication token filter.
     *
     * @return The authentication token filter.
     */
    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }
}
