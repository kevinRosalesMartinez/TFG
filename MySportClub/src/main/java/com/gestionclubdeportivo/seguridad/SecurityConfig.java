package com.gestionclubdeportivo.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestionclubdeportivo.servicio.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserService userDetailsService() {
		return new UserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/static/**","/images/**", "/equipo/todosvenEquipos", "/equipo/detalleEquipo","/equipo//export/pdf",
						"/", "/inicio", "/login", "/club", "/verequipos/**", "/persona/registro",
						"/persona/registro/submit")
				.permitAll()
				.antMatchers("/persona/**").authenticated()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/inicio", true)
				.permitAll().and().logout().logoutSuccessUrl("/").permitAll();
	}

}
