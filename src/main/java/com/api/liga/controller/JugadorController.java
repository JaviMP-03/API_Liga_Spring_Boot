package com.api.liga.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.liga.dto.JugadorDTO;
import com.api.liga.model.Jugador;
import com.api.liga.model.Posicion;
import com.api.liga.service.JugadorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

	private final JugadorService jugadorService;

	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	@GetMapping
	public ResponseEntity<List<JugadorDTO>> obtenerTodos() {
		List<Jugador> jugadores = jugadorService.obtenerJugadores();
		List<JugadorDTO> lista = new ArrayList<>();

		for (Jugador j : jugadores) {
			lista.add(jugadorService.convertirADTO(j));
		}

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JugadorDTO> obtenerPorId(@PathVariable Long id) {
		Jugador j = jugadorService.obtenerJugador(id);
		return ResponseEntity.ok(jugadorService.convertirADTO(j));
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<JugadorDTO> obtenerPorNombre(@PathVariable String nombre) {
		Jugador j = jugadorService.obtenerJugador(nombre);
		return ResponseEntity.ok(jugadorService.convertirADTO(j));
	}

	@GetMapping("/equipo/{nombreEquipo}")
	public ResponseEntity<List<JugadorDTO>> obtenerPorEquipo(@PathVariable String nombreEquipo) {
		List<Jugador> jugadores = jugadorService.obtenerJugadores(nombreEquipo);
		List<JugadorDTO> lista = new ArrayList<>();

		for (Jugador j : jugadores) {
			lista.add(jugadorService.convertirADTO(j));
		}

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/posicion/{posicion}")
	public ResponseEntity<List<JugadorDTO>> obtenerPorPosicion(@PathVariable Posicion posicion) {
		List<Jugador> jugadores = jugadorService.obtenerJugadores(posicion);
		List<JugadorDTO> lista = new ArrayList<>();

		for (Jugador j : jugadores) {
			lista.add(jugadorService.convertirADTO(j));
		}

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/equipo/id/{idEquipo}")
	public ResponseEntity<List<JugadorDTO>> mostrarJugadoresSegunEquipo(@PathVariable Long idEquipo) {
		List<Jugador> jugadores = jugadorService.mostrarJugadoresSegunEquipo(idEquipo);
		List<JugadorDTO> lista = new ArrayList<>();

		for (Jugador j : jugadores) {
			lista.add(jugadorService.convertirADTO(j));
		}

		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<JugadorDTO> crearJugador(@Valid @RequestBody JugadorDTO dto) {
		Jugador j = jugadorService.convertirADominio(dto);
		Jugador creado = jugadorService.crearJugador(j);
		return ResponseEntity.ok(jugadorService.convertirADTO(creado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<JugadorDTO> actualizarJugador(@PathVariable Long id, @Valid @RequestBody JugadorDTO dto) {
		Jugador j = jugadorService.convertirADominio(dto);
		Jugador actualizado = jugadorService.actualizarJugador(id, j);
		return ResponseEntity.ok(jugadorService.convertirADTO(actualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarJugador(@PathVariable Long id) {
		jugadorService.borrarJugador(id);
		return ResponseEntity.noContent().build();
	}
}
