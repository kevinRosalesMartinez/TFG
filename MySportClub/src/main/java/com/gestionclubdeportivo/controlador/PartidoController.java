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
import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.Partido;
import com.gestionclubdeportivo.servicio.CategoriaService;
import com.gestionclubdeportivo.servicio.EquipoService;
import com.gestionclubdeportivo.servicio.PartidoService;

@Controller
@RequestMapping("/partido")
public class PartidoController {

	@Autowired
	PartidoService partidoservice;

	@Autowired
	CategoriaService categoriaservice;

	@Autowired
	EquipoService equiposervice;

	@GetMapping("/categorias")
	public String seleccionCategoria(Model model) {

		model.addAttribute("listacategorias", categoriaservice.findAll());
		model.addAttribute("categoria", new Categoria());
		return "verPartidos";
	}

	@GetMapping(path = "/nuevoPartido/{id}")
	public String nuevoPartido(Model model, @PathVariable Long id) {

		Equipo equipo = equiposervice.findById(id);
		model.addAttribute("equipo", equipo);
		model.addAttribute("partido", new Partido());

		return "nuevoPartido";
	}

	@GetMapping(path = "/eliminar/{id}")
	public String eliminarPartido(Model model, @PathVariable Long id) {

		partidoservice.deleted(id);

		return "redirect:/equipo/verequipos";
	}

	@GetMapping(path = "/editarPartido/{id}")
	public String editarPartido(Model model, @PathVariable Long id) {

		Partido partido = partidoservice.findById(id);

		Equipo equipo = partido.getEquipopartidos();

		model.addAttribute("partido", partido);
		model.addAttribute("equipo", equipo);

		return "editarPartido";
	}

	@PostMapping(path = "/editarPartido/submit")
	public String editarPartidoSubmit(Model model, @ModelAttribute("partido") Partido editPartido,
			@RequestParam(value = "equipoID") Long id) {

		Equipo equipo = equiposervice.findById(id);
		editPartido.setEquipopartidos(equipo);

		Partido p = partidoservice.findById(editPartido.getId());
		
		p.setFechapartido(editPartido.getFechapartido());
		p.setRival(editPartido.getRival());
		
		partidoservice.save(p);
		
		return "redirect:/equipo/verequipos";
	}

	@PostMapping(path = "/nuevoPartido/submit")
	public String nuevoPartidoSubmit(Model model, @ModelAttribute("partido") Partido nuevoPartido,
			@RequestParam(value = "equipoID") Long id) {

		Equipo equipo = equiposervice.findById(id);

		nuevoPartido.setEquipopartidos(equipo);
		partidoservice.save(nuevoPartido);

//		model.addAttribute("listacategorias", categoriaservice.findAll());
//		model.addAttribute("categoria", new Categoria());
		return "redirect:/equipo/verequipos";
	}

	@PostMapping("/verPartidosCategoria")
	public String verPartidosCategoria(Model model, @ModelAttribute("categoria") Categoria categ) {
		model.addAttribute("listacategorias", categoriaservice.findAll());
		Categoria categoria = categoriaservice.findById(categ.getId());

		model.addAttribute("categoria", categoria);

		model.addAttribute("listaequipos", equiposervice.findAllByCategoria(categoria));

		return "verPartidos";
	}

}
