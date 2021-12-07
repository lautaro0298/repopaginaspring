package com.pag1;

import com.pag1.servicios.UsuarioServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PagApplication {
@Autowired
UsuarioServi usuarioServi;
	public static void main(String[] args) {
		SpringApplication.run(PagApplication.class, args);
	  
        }
           @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioServi)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}