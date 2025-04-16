package com.Osom.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     /*   http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults());
    return http.build();*/
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers( // Add all public endpoints here
                                "/product/**", "/payment/**" , "/user/**", "/dashboard"// Allow any endpoint under /public
                        ).permitAll()// Other endpoints require authentication
                )
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/dashboard", false));
                // Redirect after successful login

            http.csrf(AbstractHttpConfigurer::disable);
        return http.build();


    //by doing this we are telling hey we want to use oauth2login for login
    }
}

