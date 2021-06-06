package com.gestionclubdeportivo.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.Rol;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.servicio.EquipoService;
import com.gestionclubdeportivo.servicio.PersonaService;
import com.gestionclubdeportivo.servicio.UserService;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private UserService userservice;

	@Autowired
	private EquipoService equiposervice;

	// Crear nuevo usuario
	@GetMapping("/registro")
	public String nuevaPersona(Model model) {
		model.addAttribute("personaRegistro", new Persona());
		return "registro";
	}

	@GetMapping(path = "/{username}")
	public String verPefilPersonaEditar(Model model, @PathVariable String username) {
		User user = userservice.findByUsername(username);

		Persona persona = personaService.findByUser(user);
		model.addAttribute("persona", persona);

		// Lista de los equipos
		model.addAttribute("listaequipos", equiposervice.findAll());

		// Lista de los roles
		List<String> roles = Rol.returnRoles();
		model.addAttribute("listaroles", roles);
		return "verPerfilPersona";
	}

	@PostMapping("/registro/submit")
	public String registroSubmit(@Valid @ModelAttribute("personaRegistro") Persona nuevaPersona,
			BindingResult result, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {

		if (result.hasErrors()) {
			return "registro";
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);

		User user = new User(username, encodedPassword);
		userservice.save(user);

		nuevaPersona.setUser(user);
		personaService.save(nuevaPersona);

		return ("redirect:/");
	}

	@PostMapping("/editar/submit")
	public String editarPerfilPersona(Model model, @Valid @ModelAttribute("persona") Persona editarPersona,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("listaequipos", equiposervice.findAll());
			// Lista de los roles
			List<String> roles = Rol.returnRoles();
			model.addAttribute("listaroles", roles);
			return "verPerfilPersona";
		}
		Persona p = personaService.findById(editarPersona.getId()).orElse(null);

		p.setNombre(editarPersona.getNombre());
		p.setApellidos(editarPersona.getApellidos());
		p.setDni(editarPersona.getDni());
		p.setEmail(editarPersona.getEmail());
		p.setTelefono(editarPersona.getTelefono());
		p.setFechanacimiento(editarPersona.getFechanacimiento());
		p.setEquipo(editarPersona.getEquipo());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(editarPersona.getUser().getPassword());

		p.getUser().setusername(editarPersona.getUser().getUsername());
		p.getUser().setPassword(encodedPassword);
		p.getUser().setRol(editarPersona.getUser().getRol());
		personaService.save(p);

		return "redirect:/user/verusuarios";
	}

}
