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

import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.Rol;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.servicio.PersonaService;
import com.gestionclubdeportivo.servicio.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;

	@Autowired
	PersonaService personaservice;

	@GetMapping("/verusuarios")
	public String listaUsuarios(Model model) {
		model.addAttribute("listausuarios", userservice.findAllByOrderByRol());
		return "verTodosUsuarios";
	}

	@GetMapping(path = "/{id}")
	public String verPefilPersona(Model model, @PathVariable Long id) {

		User user = userservice.findById(id);
		model.addAttribute("user", user);

		List<String> roles = Rol.returnRoles();

		model.addAttribute("listaroles", roles);

		return "verPerfilUsuario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model, @PathVariable Long id) {

		User user = userservice.findById(id);
		Persona p = personaservice.findByUser(user);

		personaservice.deleteById(p.getId());
		userservice.deleteById(user.getId());

		return "redirect:/user/verusuarios";
	}

}
