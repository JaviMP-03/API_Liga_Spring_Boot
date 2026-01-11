package com.api.liga.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.liga.dto.EquipoDTO;
import com.api.liga.model.Equipo;
import com.api.liga.service.EquipoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

	private final EquipoService equipoService;

	public EquipoController(EquipoService equipoService) {
		this.equipoService = equipoService;
	}

	@GetMapping
	public ResponseEntity<List<EquipoDTO>> obtenerTodos() {
		List<Equipo> equipos = equipoService.obtenerTodos();
		List<EquipoDTO> lista = new ArrayList<>();

		for (Equipo e : equipos) {
			lista.add(equipoService.convertirADTO(e));
		}

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EquipoDTO> obtenerPorId(@PathVariable Long id) {
		Equipo e = equipoService.obtenerEquipo(id);
		return ResponseEntity.ok(equipoService.convertirADTO(e));
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<EquipoDTO> obtenerPorNombre(@PathVariable String nombre) {
		Equipo e = equipoService.obtenerEquipo(nombre);
		return ResponseEntity.ok(equipoService.convertirADTO(e));
	}

	@PostMapping
	public ResponseEntity<EquipoDTO> crearEquipo(@Valid @RequestBody EquipoDTO dto) {
		Equipo e = equipoService.convertirADominio(dto);
		Equipo creado = equipoService.crearEquipo(e);
		return ResponseEntity.ok(equipoService.convertirADTO(creado));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EquipoDTO> actualizarEquipo(@PathVariable Long id, @Valid @RequestBody EquipoDTO dto) {
		Equipo e = equipoService.convertirADominio(dto);
		Equipo actualizado = equipoService.actualizarEquipo(id, e);
		return ResponseEntity.ok(equipoService.convertirADTO(actualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
		equipoService.eliminarEquipo(id);
		return ResponseEntity.noContent().build();
	}
}
