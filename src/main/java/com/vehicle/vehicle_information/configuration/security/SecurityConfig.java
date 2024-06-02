package com.vehicle.vehicle_information.configuration.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

    auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USERS").and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN","USERS");


}

public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}





}
