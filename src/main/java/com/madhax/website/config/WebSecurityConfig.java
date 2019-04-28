package com.madhax.website.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DataSource dataSource;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
    }

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("Attemping authentication.");
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.debug("Authenticating request.");
        http.
                authorizeRequests()
                .antMatchers("/project/new",
                        "/project/edit",
                        "/project/edit/*",
                        "/project/delete",
                        "/project/delete/*").hasRole("ADMIN")
                .antMatchers("/project/note/new/*",
                        "/project/note/edit",
                        "/project/note/edit/**",
                        "/project/note/delete",
                        "/project/note/delete/**").hasRole("ADMIN")
                .antMatchers("/project/feature/new/*",
                        "/project/feature/edit",
                        "/project/feature/edit/**",
                        "/project/feature/delete",
                        "/project/feature/delete/**").hasRole("ADMIN")
                .antMatchers("/project/issue/new",
                        "/project/issue/edit",
                        "/project/issue/delete").hasAnyRole()
                .antMatchers("/",
                        "/bstest",
                        "/about",
                        "/contact",
                        "/project",
                        "/project/*").permitAll()
                .anyRequest().authenticated().and().formLogin();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/image/**");
    }

}
