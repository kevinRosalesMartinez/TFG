package com.gestionclubdeportivo.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gestionclubdeportivo.entidad.Categoria;
import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.servicio.CategoriaService;
import com.gestionclubdeportivo.servicio.EquipoService;
import com.gestionclubdeportivo.servicio.PersonaService;
import com.gestionclubdeportivo.servicio.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userservice;

	@Autowired
	PersonaService personaservice;

	@Autowired
	CategoriaService categoriaservice;

	@Autowired
	EquipoService equiposervice;

	@GetMapping({ "/", "/inicio" })
	public String blanco() {
		return "/inicio";
	}

	@GetMapping("/club")
	public String Club(Model model) {
		return "club";
	}

	@GetMapping(path = "/verequipos/categorias/{categoria}")
	public String verEquiposCategoria(Model model, @PathVariable int categoria) {
		List<Equipo> listaequipo = new ArrayList<>();
		Categoria c;

		switch (categoria) {
		case 1:// Senior
			c = categoriaservice.findById((long) 1);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		case 2:// Junior
			c = categoriaservice.findById((long) 2);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		case 3:// Cadete
			c = categoriaservice.findById((long) 3);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		case 4:// Infantil
			c = categoriaservice.findById((long) 4);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		case 5:// Alevin
			c = categoriaservice.findById((long) 5);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		case 6:// Benjamin
			c = categoriaservice.findById((long) 6);
			listaequipo = equiposervice.findAllByCategoria(c);
			model.addAttribute("listaequipos", equiposervice.findAllByCategoria(c));
			model.addAttribute("equipo", new Equipo());
			return "seleccionarEquipo";
		default:
			return "/inicio";
		}

	}

}
