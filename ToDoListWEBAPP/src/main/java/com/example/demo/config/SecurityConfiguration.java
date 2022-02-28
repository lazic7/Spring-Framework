package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration {
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("user").password("password").roles("USER", "ADMIN"); 
    }

    //koristim hardcoded username i password dok ne uvedem bazu
    
    /*Deprecated This PasswordEncoder is not secure. 
    Instead use an adaptive one way function like BCryptPasswordEncoder, Pbkdf2PasswordEncoder, or SCryptPasswordEncoder. 
    Even better use DelegatingPasswordEncoder which supports password upgrades. 
    There are no plans to remove this support. 
    It is deprecated to indicate that this is a legacy implementation and using it is considered insecure.

    This PasswordEncoder is provided for legacy and testing purposes only and is not considered secure. 
    A password encoder that does nothing. 
    Useful for testing where working with plain text passwords may be preferred. 
    
    Keith Donald*/

}
