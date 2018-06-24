package ua.in.poddyachiy.organization.project.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String ROLE_MODIFICATION = "MODIFICATION";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_READ_ONLY = "READ_ONLY";

    @Autowired
    private PlainTextBasicAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST).hasRole(ROLE_MODIFICATION)
                .antMatchers(HttpMethod.PATCH).hasRole(ROLE_MODIFICATION)
                .antMatchers(HttpMethod.DELETE).hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Eric").password("{noop}password12345").roles(ROLE_READ_ONLY)
                .and()
                .withUser("Kenny").password("{noop}password12345").roles(ROLE_READ_ONLY, ROLE_MODIFICATION)
                .and()
                .withUser("Chef").password("{noop}password12345").roles(ROLE_READ_ONLY, ROLE_MODIFICATION, ROLE_ADMIN);

    }
}
