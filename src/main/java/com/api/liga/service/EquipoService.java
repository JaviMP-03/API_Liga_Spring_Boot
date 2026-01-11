package com.api.liga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.liga.dto.EquipoDTO;
import com.api.liga.exceptions.RecursoDuplicado;
import com.api.liga.exceptions.RecursoNoEncontrado;
import com.api.liga.model.Equipo;
import com.api.liga.repository.EquipoRepository;

@Service
public class EquipoService {

	private final EquipoRepository equipoRepository;

	public EquipoService(EquipoRepository equipoRepository) {
		this.equipoRepository = equipoRepository;
	}

	// --- GET ---

	public List<Equipo> obtenerTodos() {
		return equipoRepository.findAll();
	}

	public Equipo obtenerEquipo(Long id) {
		Optional<Equipo> equipo = equipoRepository.findById(id);

		if (!equipo.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el equipo con id " + id);
		}

		return equipo.get();
	}

	public Equipo obtenerEquipo(String nombre) {
		Optional<Equipo> equipo = equipoRepository.findByNombre(nombre);

		if (!equipo.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el equipo con nombre " + nombre);
		}

		return equipo.get();
	}

	// --- POST ---

	public Equipo crearEquipo(Equipo equipo) {

		if (equipoRepository.existsByNombre(equipo.getNombre())) {
			throw new RecursoDuplicado("Ya existe un equipo con ese nombre");
		}

		if (equipoRepository.existsByNombreEstadio(equipo.getNombreEstadio())) {
			throw new RecursoDuplicado("Ya existe un equipo con ese estadio");
		}

		if (equipoRepository.existsByNombreEntrenador(equipo.getNombreEntrenador())) {
			throw new RecursoDuplicado("Ya existe un equipo con ese entrenador");
		}

		return equipoRepository.save(equipo);
	}

	// --- PUT ---

	public Equipo actualizarEquipo(Long id, Equipo nuevoEquipo) {

		Optional<Equipo> equipoOptional = equipoRepository.findById(id);

		if (!equipoOptional.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el equipo con id " + id);
		}

		Equipo equipo = equipoOptional.get();

		// ---- NOMBRE ----
		String nombreActual = equipo.getNombre().trim();
		String nombreNuevo = nuevoEquipo.getNombre().trim();

		if (!nombreActual.equalsIgnoreCase(nombreNuevo)) {

			Optional<Equipo> equipoConNombre = equipoRepository.findByNombre(nombreNuevo);

			if (equipoConNombre.isPresent()) {
				Equipo existe = equipoConNombre.get();

				if (!existe.getId().equals(equipo.getId())) {
					throw new RecursoDuplicado("El nombre del equipo ya está en uso");
				}
			}

			equipo.setNombre(nombreNuevo);
		}

		// ---- ESTADIO ----
		String estadioActual = equipo.getNombreEstadio().trim();
		String estadioNuevo = nuevoEquipo.getNombreEstadio().trim();

		if (!estadioActual.equalsIgnoreCase(estadioNuevo)) {

			if (equipoRepository.existsByNombreEstadioAndIdNot(estadioNuevo, equipo.getId())) {
				throw new RecursoDuplicado("El nombre del estadio ya está en uso");
			}

			equipo.setNombreEstadio(estadioNuevo);
		}

		// ---- ENTRENADOR ----
		String entrenadorActual = equipo.getNombreEntrenador().trim();
		String entrenadorNuevo = nuevoEquipo.getNombreEntrenador().trim();

		if (!entrenadorActual.equalsIgnoreCase(entrenadorNuevo)) {

			if (equipoRepository.existsByNombreEntrenadorAndIdNot(entrenadorNuevo, equipo.getId())) {
				throw new RecursoDuplicado("El nombre del entrenador ya está en uso");
			}

			equipo.setNombreEntrenador(entrenadorNuevo);
		}

		// ---- CAMPOS SIN UNICIDAD ----
		equipo.setFechaFundacion(nuevoEquipo.getFechaFundacion());
		equipo.setCapacidad(nuevoEquipo.getCapacidad());

		return equipoRepository.save(equipo);
	}

	// --- DELETE ---

	public void eliminarEquipo(Long id) {

		Optional<Equipo> equipo = equipoRepository.findById(id);

		if (!equipo.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el equipo con id " + id);
		}

		equipoRepository.delete(equipo.get());
	}

	// --- DTO & DOMINIO

	public Equipo convertirADominio(EquipoDTO eDTO) {
		Equipo e = new Equipo(eDTO.getNombre(), eDTO.getFechaFundacion(), eDTO.getNombreEstadio(), eDTO.getCapacidad(),
				eDTO.getNombreEntrenador());
		return e;
	}

	public EquipoDTO convertirADTO(Equipo e) {
		EquipoDTO eDTO = new EquipoDTO(e.getId(), e.getNombre(), e.getFechaFundacion(), e.getNombreEstadio(),
				e.getCapacidad(), e.getNombreEntrenador());
		return eDTO;
	}
}
