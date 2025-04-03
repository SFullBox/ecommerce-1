package com.senai.ecommerce.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //arquivo de configuração
public class SecurityConfiguration {
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	  @Bean //permite a utlilização de todas as rotas
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	    	http.csrf(csrf -> csrf.disable());
	    	http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
	    	return http.build();
	    }

	
	
}
