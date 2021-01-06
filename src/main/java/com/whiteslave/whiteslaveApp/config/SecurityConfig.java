package com.whiteslave.whiteslaveApp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${swagger.enabled:false}")
    private boolean enableSwagger;
    private boolean enableSecurity = true;

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/configuration/**",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/swagger-ui.html#/**",
            "/swagger-ui.html#/*",
            "/swagger-ui/**",
            "/swagger-ui.html#/swagger-welcome-controller/**",
            "/webjars/springfox-swagger-ui/**",
            "/webjars/**",
            "/csrf",
            "/",
            "/sw",
            "/hal",};

    private static final String[] AUTH_LIST = {
            "/reportquery/**", "/check/**", "/search/**", "/user/**"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,AUTH_LIST)
                .permitAll().anyRequest().authenticated()
                .and().httpBasic();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        if (enableSwagger) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,AUTH_WHITELIST);
//        }
//        if (!enableSecurity) {
//            web.ignoring().anyRequest();
//        }
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("matiej")
                .password("{noop}test123")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN");

    }


}
