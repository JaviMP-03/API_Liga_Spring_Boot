package com.api.liga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.liga.dto.JugadorDTO;
import com.api.liga.exceptions.RecursoDuplicado;
import com.api.liga.exceptions.RecursoNoEncontrado;
import com.api.liga.model.Equipo;
import com.api.liga.model.Jugador;
import com.api.liga.model.Posicion;
import com.api.liga.repository.EquipoRepository;
import com.api.liga.repository.JugadorRepository;

@Service
public class JugadorService {

	private final JugadorRepository jugadorRepository;
	private final EquipoRepository equipoRepository;

	public JugadorService(JugadorRepository jugadorRepository, EquipoRepository equipoRepository) {
		this.jugadorRepository = jugadorRepository;
		this.equipoRepository = equipoRepository;
	}

	public List<Jugador> obtenerJugadores() {
		return jugadorRepository.findAll();
	}

	public Jugador obtenerJugador(Long id) {
		Optional<Jugador> jugador = jugadorRepository.findById(id);

		if (!jugador.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el jugador con id " + id);
		}

		return jugador.get();
	}

	public Jugador obtenerJugador(String nombre) {
		Optional<Jugador> jugador = jugadorRepository.findByNombre(nombre);

		if (!jugador.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el jugador con nombre " + nombre);
		}

		return jugador.get();
	}

	public List<Jugador> obtenerJugadores(String nombreEquipo) {
		return jugadorRepository.findByEquipoNombre(nombreEquipo);
	}

	public List<Jugador> obtenerJugadores(Posicion posicion) {
		return jugadorRepository.findByPosicion(posicion);
	}

	public List<Jugador> mostrarJugadoresSegunEquipo(Long idEquipo) {
		Optional<Equipo> equipo = equipoRepository.findById(idEquipo);

		if (!equipo.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el equipo");
		}

		return jugadorRepository.findByEquipo(equipo.get());
	}

	public List<Jugador> obtenerJugadoresPorEquipo(Long idEquipo) {
		return jugadorRepository.findByEquipoId(idEquipo);
	}

	// --- POST ---

	public Jugador crearJugador(Jugador jugador) {

		Optional<Equipo> equipo = equipoRepository.findById(jugador.getEquipo().getId());

		if (!equipo.isPresent()) {
			throw new RecursoNoEncontrado("El equipo indicado no existe");
		}

		Equipo equipoReal = equipo.get();

		if (jugadorRepository.existsByNombreAndEquipo(jugador.getNombre(), equipoReal)) {
			throw new RecursoDuplicado("Ya existe un jugador con ese nombre en el equipo");
		}

		if (jugadorRepository.existsByDorsalAndEquipo(jugador.getDorsal(), equipoReal)) {
			throw new RecursoDuplicado("Ese dorsal ya está en uso en el equipo");
		}

		jugador.setEquipo(equipoReal);

		return jugadorRepository.save(jugador);
	}

	// --- PUT ---

	public Jugador actualizarJugador(Long id, Jugador nuevoJugador) {

		Optional<Jugador> jugadorOptional = jugadorRepository.findById(id);

		if (!jugadorOptional.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el jugador");
		}

		Jugador jugador = jugadorOptional.get();

		Optional<Equipo> equipoNuevoOpt = equipoRepository.findById(nuevoJugador.getEquipo().getId());

		if (!equipoNuevoOpt.isPresent()) {
			throw new RecursoNoEncontrado("El equipo indicado no existe");
		}

		Equipo equipoNuevo = equipoNuevoOpt.get();
		Equipo equipoActual = jugador.getEquipo();

		boolean cambiaEquipo = !equipoActual.getId().equals(equipoNuevo.getId());

		if (!jugador.getNombre().equalsIgnoreCase(nuevoJugador.getNombre()) || cambiaEquipo) {
			if (jugadorRepository.existsByNombreAndEquipoAndIdNot(nuevoJugador.getNombre(), equipoNuevo,
					jugador.getId())) {
				throw new RecursoDuplicado("Ya existe un jugador con ese nombre en el equipo");
			}
			jugador.setNombre(nuevoJugador.getNombre());
		}

		if (jugador.getDorsal() != nuevoJugador.getDorsal() || cambiaEquipo) {
			if (jugadorRepository.existsByDorsalAndEquipoAndIdNot(nuevoJugador.getDorsal(), equipoNuevo,
					jugador.getId())) {
				throw new RecursoDuplicado("Ese dorsal ya está en uso en el equipo");
			}
			jugador.setDorsal(nuevoJugador.getDorsal());
		}

		if (cambiaEquipo) {
			jugador.setEquipo(equipoNuevo);
		}

		jugador.setFechaNacimiento(nuevoJugador.getFechaNacimiento());
		jugador.setSalario(nuevoJugador.getSalario());
		jugador.setPosicion(nuevoJugador.getPosicion());

		return jugadorRepository.save(jugador);
	}

	// --- DELETE ---

	public void borrarJugador(Long id) {

		Optional<Jugador> jugador = jugadorRepository.findById(id);

		if (!jugador.isPresent()) {
			throw new RecursoNoEncontrado("No se ha encontrado el jugador");
		}

		jugadorRepository.delete(jugador.get());
	}

	// --- DTO & DOMINIO

	public Jugador convertirADominio(JugadorDTO jDTO) {
		Jugador j = new Jugador();
		j.setNombre(jDTO.getNombre());
		j.setFechaNacimiento(jDTO.getFechaNacimiento());
		j.setDorsal(jDTO.getDorsal());
		j.setSalario(jDTO.getSalario());
		j.setPosicion(jDTO.getPosicion());

		Optional<Equipo> equipoBuscado = equipoRepository.findByNombre(jDTO.getEquipo());
		if (!equipoBuscado.isPresent()) {
			throw new RecursoNoEncontrado("No se encontró el equipo: " + jDTO.getEquipo());
		}

		j.setEquipo(equipoBuscado.get());

		return j;
	}

	public JugadorDTO convertirADTO(Jugador j) {
		JugadorDTO jDTO = new JugadorDTO(j.getId(), j.getNombre(), j.getFechaNacimiento(), j.getDorsal(),
				j.getSalario(), j.getPosicion(), j.getEquipo().getNombre(), j.getEdad());
		return jDTO;
	}
}
