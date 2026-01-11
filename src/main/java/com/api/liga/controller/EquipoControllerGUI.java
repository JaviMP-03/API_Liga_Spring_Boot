package com.api.liga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.liga.model.Equipo;
import com.api.liga.service.EquipoService;
import com.api.liga.service.JugadorService;

@Controller
@RequestMapping("/equiposGUI")
public class EquipoControllerGUI {

	private final EquipoService equipoService;
	private final JugadorService jugadorService;

	public EquipoControllerGUI(EquipoService equipoService, JugadorService jugadorService) {
		this.equipoService = equipoService;
		this.jugadorService = jugadorService;
	}

	// LISTAR
	@GetMapping
	public String listarEquipos(Model model) {
		model.addAttribute("equipos", equipoService.obtenerTodos());
		return "equipos/index";
	}

	// NUEVO FORM
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(Model model) {
		model.addAttribute("equipo", new Equipo());
		return "equipos/formulario";
	}

	// GUARDAR
	@PostMapping("/guardar")
	public String guardarEquipo(@ModelAttribute Equipo equipo) {
		if (equipo.getId() != null) {
			equipoService.actualizarEquipo(equipo.getId(), equipo);
		} else {
			equipoService.crearEquipo(equipo);
		}
		return "redirect:/equiposGUI";
	}

	// EDITAR
	@GetMapping("/editar/{id}")
	public String editarEquipo(@PathVariable Long id, Model model) {
		model.addAttribute("equipo", equipoService.obtenerEquipo(id));
		return "equipos/formulario";
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarEquipo(@PathVariable Long id) {
		equipoService.eliminarEquipo(id);
		return "redirect:/equiposGUI";
	}

	// JUGADORES POR EQUIPO (texto)
	@GetMapping("/jugadores/{idEquipo}")
	@ResponseBody
	public String jugadoresPorEquipo(@PathVariable Long idEquipo) {
		StringBuilder sb = new StringBuilder();
		jugadorService.mostrarJugadoresSegunEquipo(idEquipo).forEach(j -> sb.append(j.getNombre()).append(" - ")
				.append(j.getPosicion()).append(" - Edad: ").append(j.getEdad()).append("\n"));
		return sb.toString();
	}
}