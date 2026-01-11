package com.api.liga.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.api.liga.model.Jugador;
import com.api.liga.model.Posicion;
import com.api.liga.service.EquipoService;
import com.api.liga.service.JugadorService;

@Controller
@RequestMapping("/jugadoresGUI")
public class JugadorControllerGUI {

	private final JugadorService jugadorService;
	private final EquipoService equipoService;

	public JugadorControllerGUI(JugadorService jugadorService, EquipoService equipoService) {
		this.jugadorService = jugadorService;
		this.equipoService = equipoService;
	}

	// LISTA TODOS
	@GetMapping("/todos")
	public String mostrarTodosJugadores(Model model) {
		model.addAttribute("jugadores", jugadorService.obtenerJugadores());
		return "jugadores/todosJugadores";
	}

	// NUEVO FORM
	@GetMapping("/nuevo")
	public String mostrarFormularioNuevo(@RequestParam(value = "pagina", required = false) String pagina, Model model) {

		model.addAttribute("jugador", new Jugador());
		model.addAttribute("equipos", equipoService.obtenerTodos());
		model.addAttribute("posiciones", Posicion.values());

		if (pagina != null) {
			model.addAttribute("pagina", pagina);
		} else {
			model.addAttribute("pagina", "todos");
		}

		return "jugadores/nuevoJugador";
	}

	// EDITAR FORM
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id,
			@RequestParam(value = "pagina", required = false) String pagina, Model model) {

		model.addAttribute("jugador", jugadorService.obtenerJugador(id));
		model.addAttribute("equipos", equipoService.obtenerTodos());
		model.addAttribute("posiciones", Posicion.values());

		if (pagina != null) {
			model.addAttribute("pagina", pagina);
		} else {
			model.addAttribute("pagina", "todos");
		}

		return "jugadores/nuevoJugador";
	}

	// GUARDAR
	@PostMapping("/guardar")
	public String guardarJugador(@ModelAttribute Jugador jugador, @RequestParam("pagina") String pagina) {
		if (jugador.getId() == null) {
			jugadorService.crearJugador(jugador);
		} else {
			jugadorService.actualizarJugador(jugador.getId(), jugador);
		}
		return "redirect:/jugadoresGUI/" + pagina;
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminarJugador(@PathVariable Long id, @RequestParam("pagina") String pagina) {
		jugadorService.borrarJugador(id);
		return "redirect:/jugadoresGUI/" + pagina;
	}

	// JUGADORES POR EQUIPO (JSON)
	@GetMapping("/equipoTexto/{id}")
	@ResponseBody
	public List<Map<String, Object>> jugadoresPorEquipoTexto(@PathVariable Long id) {
		List<Jugador> jugadores = jugadorService.obtenerJugadoresPorEquipo(id);
		List<Map<String, Object>> resultado = new ArrayList<>();
		for (Jugador j : jugadores) {
			Map<String, Object> fila = new HashMap<>();
			fila.put("nombre", j.getNombre());
			fila.put("edad", j.getEdad());
			fila.put("posicion", j.getPosicion());
			fila.put("salario", j.getSalario());
			fila.put("dorsal", j.getDorsal());
			resultado.add(fila);
		}
		return resultado;
	}
}
