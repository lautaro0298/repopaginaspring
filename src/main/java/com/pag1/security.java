package com.pag1;

import com.pag1.servicios.UsuarioServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)

public class security extends WebSecurityConfigurerAdapter {
    @Autowired
    public UsuarioServi usu;
    @Autowired 
    public void configureGlobal(AuthenticationManagerBuilder aut)throws Exception{
        aut
        .userDetailsService(usu)
          .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
                .and().formLogin()
                .loginPage("/login") // Que formulario esta mi login 
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email") // Como viajan los datos del logueo
                .passwordParameter("password")// Como viajan los datos del logueo
                .defaultSuccessUrl("/") // A que URL viaja  
                .permitAll()
                .and().logout() // Aca configuro la salida 
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll().and().csrf().disable();
    }
}
