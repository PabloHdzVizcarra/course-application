package jvm.pablohdz.courseapplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Manages the security settings of the entire application
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement()
                .sessionCreationPolicy(STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/course")
                .hasAnyAuthority("ROLE_ADMIN");

        http.authorizeRequests()
                .antMatchers("/api/**")
                .permitAll();

    }
}
