package com.gestionclubdeportivo.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionclubdeportivo.entidad.Equipo;
import com.gestionclubdeportivo.entidad.EquipoPDFExporter;
import com.gestionclubdeportivo.entidad.Persona;
import com.gestionclubdeportivo.entidad.Rol;
import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.servicio.CategoriaService;
import com.gestionclubdeportivo.servicio.EquipoService;
import com.gestionclubdeportivo.servicio.PartidoService;
import com.gestionclubdeportivo.servicio.PersonaService;
import com.gestionclubdeportivo.servicio.UserService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	EquipoService equiposervice;

	@Autowired
	CategoriaService categoriaservice;

	@Autowired
	PartidoService partidoservice;

	@Autowired
	PersonaService personaservice;

	@Autowired
	UserService userservice;

	@GetMapping("verequipos")
	public String listaEquipos(Model model) {
		model.addAttribute("listaequipos", equiposervice.findAllByOrderByCategoria());

		return "verTodosEquipos";
	}

	@GetMapping(path = "/{id}")
	public String verPefilEquipo(Model model, @PathVariable Long id) {

		Equipo equipo = equiposervice.findById(id);
		model.addAttribute("equipo", equipo);
		model.addAttribute("usuario", userservice.findByUsername("admin"));
		model.addAttribute("listacategorias", categoriaservice.findAll());

		return "verPerfilEquipo";
	}

	@GetMapping(path = "/ver/{name}")
	public String verPefilEquipo(Model model, @PathVariable String name) {
		User u;

		if (name == null) {
			u = userservice.findByUsername("admin");
		} else {
			u = userservice.findByUsername(name);
		}

		Persona p = personaservice.findByUser(u);

		Equipo equipo = p.getEquipo();

		if (equipo != null) {
			model.addAttribute("equipo", equipo);
			model.addAttribute("listacategorias", categoriaservice.findAll());

			model.addAttribute("usuario", u);

			return "verPerfilEquipo";

		} else {
			return "/inicio";
		}
	}

	@GetMapping(path = "eliminar/{id}")
	public String eliminarEquipo(Model model, @PathVariable Long id) {

		Equipo e = equiposervice.findById(id);

		List<Persona> persona = personaservice.findAllByEquipo(e);

		persona.forEach(p -> {
			p.setEquipo(null);
			personaservice.save(p);
		});

		equiposervice.delete(id);

		return "redirect:/equipo/verequipos";
	}

	@GetMapping("nuevoequipo")
	public String nuevoEquipo(Model model) {
		model.addAttribute("nuevoequipo", new Equipo());
		model.addAttribute("listacategorias", categoriaservice.findAll());
		return "crearEquipo";
	}

	@GetMapping(path = "expulsarpersona/{id}")
	public String expulsardelEquipo(Model model, @PathVariable Long id) {
		Persona p = personaservice.findById(id).orElse(null);

		p.setEquipo(null);
		personaservice.save(p);

		return "redirect:/equipo/verequipos";
	}

	@GetMapping("/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ListaEquipos_" + currentDateTime + ".pdf";

		response.setHeader(headerKey, headerValue);

		List<Equipo> listaEquipos = equiposervice.findAllByOrderByCategoria();

		EquipoPDFExporter exporter = new EquipoPDFExporter(listaEquipos);
		exporter.export(response);

	}

	@PostMapping("miembros")
	public String miembrosEquipos(Model model, @ModelAttribute("equipo") Equipo equipo) {
		Equipo e = equiposervice.findById(equipo.getId());

		// CON EL EQUIPO OBTENER LISTA PERSONAS Y MOSTRRLAS EN OTRO HTML
		List<Persona> listamiembros = personaservice.findAllByEquipo(e);

		List<Persona> listajugadores = new ArrayList<>();
		List<Persona> listaentrenadores = new ArrayList<>();

		listamiembros.forEach(le -> {
			if (le.getUser().getRol().equals(Rol.ENTRENADOR)) {
				listaentrenadores.add(le);
			}
			if (le.getUser().getRol().equals(Rol.JUGADOR)) {
				listajugadores.add(le);
			}
		});

		model.addAttribute("listaentrenadores", listaentrenadores);
		model.addAttribute("listajugadores", listajugadores);

		model.addAttribute("equipo", e);

		return "verMiembrosEquipo";
	}

	@PostMapping("nuevoequipo/submit")
	public String nuevoEquipoCrear(Model model, @Valid @ModelAttribute("nuevoequipo") Equipo nuevoEquipo,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("listacategorias", categoriaservice.findAll());
			return "crearEquipo";
		}
		equiposervice.save(nuevoEquipo);
		return "redirect:/equipo/verequipos";
	}

	@PostMapping("/editar/submit")
	public String editarEquipo(Model model, @ModelAttribute("equipo") Equipo editarEquipo) {

		Equipo e = equiposervice.findById(editarEquipo.getId());
		e.setNombre(editarEquipo.getNombre());
		e.setCategoria(editarEquipo.getCategoria());

		equiposervice.save(e);
		return "redirect:/equipo/verequipos";

	}

	@PostMapping("agregarmiembros")

	public String agregarMiembros(Model model, @ModelAttribute("equipo") Equipo equipoId) {
		Equipo e = equiposervice.findById(equipoId.getId());

		List<Persona> listamiembros = personaservice.findAll();
		List<Persona> listasinequipo = new ArrayList<>();

		listamiembros.forEach(p -> {
			if (p.getEquipo() == null) {
				listasinequipo.add(p);
			}
		});

		model.addAttribute("equipo", e);
		model.addAttribute("listasinequipo", listasinequipo);

		return "agregarMiembro";
	}

	@PostMapping("agregarmiembros/submit")
	public String agregarMiembrosSubmit(Model model, @ModelAttribute("equipo") Equipo equipoId,
			@RequestParam(name = "agregar") List<Long> personasID) {

		Equipo e = equiposervice.findById(equipoId.getId());

		personasID.forEach(id -> {
			Persona p = personaservice.findById(id).orElse(null);
			p.setEquipo(e);
			personaservice.save(p);
		});

		return "redirect:/equipo/verequipos";
	}

	@PostMapping("detalleEquipo")
	public String detalleEquipo(Model model, @ModelAttribute("equipo") Equipo equipo) {

		Equipo e = equiposervice.findById(equipo.getId());

		model.addAttribute("listaequipos", equiposervice.findAllByCategoria(e.getCategoria()));

		model.addAttribute("equipo", e);

		return "partidosEquipo";
	}
}
