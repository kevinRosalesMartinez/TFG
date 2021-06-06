package com.gestionclubdeportivo;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.Rol;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.servicio.PersonaService;
import com.gestionclubdeportivo.servicio.UserService;

@SpringBootApplication
public class GestionClubDeportivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionClubDeportivoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UserService userservice, PersonaService personaservice) {

		return (args) -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode("admin");

			// YA NO HACE FALTA METER MANUALMENTE NI USER NI PERSONA

//            User user = new User("admin",encodedPassword);
//            user.setRol(Rol.ADMIN);
//        	userservice.save(user);

//        	User user = (User) userservice.loadUserByUsername("kevin");
//        	Date date = new Date();
//        	
//            Persona persona = new Persona("kevin","rosales","dnikevin","email@kevin","1111",date,user);
//            personaservice.save(persona);
		};
	}

}
