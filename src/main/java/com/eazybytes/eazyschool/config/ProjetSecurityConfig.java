package com.eazybytes.eazyschool.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class ProjetSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("User").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN")
//                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**").
                     ignoringAntMatchers("/api/**").and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/dispayProfile").authenticated()
                 .mvcMatchers("updateProfile").authenticated()
                 .mvcMatchers("/api/**").authenticated()
                 .mvcMatchers("/public/**").permitAll()
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/displayMessages").hasRole("ADMIN")
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                 .mvcMatchers("/student/**").hasRole("STUDENT")
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").authenticated()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/login").permitAll()
                 .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();


    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
