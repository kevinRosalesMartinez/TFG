package com.gestionclubdeportivo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionclubdeportivo.entidad.Categoria;
import com.gestionclubdeportivo.entidad.Entrenamiento;
import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Partido;
import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.servicio.CategoriaService;
import com.gestionclubdeportivo.servicio.EntrenamientoService;
import com.gestionclubdeportivo.servicio.EquipoService;

@Controller
@RequestMapping("/entrenamiento")
public class EntrenamientoController {

	@Autowired
	EntrenamientoService entrenservice;

	@Autowired
	CategoriaService categoriaservice;

	@Autowired
	EquipoService equiposervice;

	@GetMapping("/categorias")
	public String seleccionCategoria(Model model) {

		model.addAttribute("listacategorias", categoriaservice.findAll());
		model.addAttribute("categoria", new Categoria());
		return "verEntrenamientos";
	}

	@GetMapping(path = "/nuevoEntrenamiento/{id}")
	public String nuevoEntrenamiento(Model model, @PathVariable Long id) {

		Equipo equipo = equiposervice.findById(id);
		model.addAttribute("equipo", equipo);
		model.addAttribute("entrenamiento", new Entrenamiento());

		return "nuevoEntrenamiento";
	}

	@GetMapping(path = "/eliminar/{id}")
	public String eliminarEntrene(Model model, @PathVariable Long id) {

		entrenservice.deleted(id);

		return "redirect:/equipo/verequipos";
	}

	@GetMapping(path = "/editarEntrenamiento/{id}")
	public String editarPartido(Model model, @PathVariable Long id) {

		Entrenamiento entrene = entrenservice.findById(id);

		Equipo equipo = entrene.getEquipoentrenamientos();

		model.addAttribute("entrenamiento", entrene);
		model.addAttribute("equipo", equipo);

		return "editarEntrenamiento";
	}

	@PostMapping(path = "/editarEntrenamiento/submit")
	public String editarPartidoSubmit(Model model, @ModelAttribute("entrenamiento") Entrenamiento editEntrene,
			@RequestParam(value = "equipoID") Long id) {

		Equipo equipo = equiposervice.findById(id);
		editEntrene.setEquipoentrenamientos(equipo);

		Entrenamiento e = entrenservice.findById(editEntrene.getId());

		e.setFechaentrenamiento(editEntrene.getFechaentrenamiento());

		entrenservice.save(e);

		return "redirect:/equipo/verequipos";
	}

	@PostMapping(path = "/nuevoEntrenamiento/submit")
	public String nuevoEntrenamientoSubmit(Model model,
			@ModelAttribute("entrenamiento") Entrenamiento nuevoEntrenamiento,
			@RequestParam(value = "equipoID") Long id) {

		Equipo equipo = equiposervice.findById(id);

		nuevoEntrenamiento.setEquipoentrenamientos(equipo);
		entrenservice.save(nuevoEntrenamiento);

		model.addAttribute("listacategorias", categoriaservice.findAll());
		model.addAttribute("categoria", new Categoria());
		return "redirect:/equipo/verequipos";
	}

	@PostMapping("/verEntrenamientosCategoria")
	public String verPartidosCategoria(Model model, @ModelAttribute("categoria") Categoria categ) {

		model.addAttribute("listacategorias", categoriaservice.findAll());
		Categoria categoria = categoriaservice.findById(categ.getId());

		model.addAttribute("categoria", categoria);

		model.addAttribute("listaequipos", equiposervice.findAllByCategoria(categoria));

		return "verEntrenamientos";
	}

}
