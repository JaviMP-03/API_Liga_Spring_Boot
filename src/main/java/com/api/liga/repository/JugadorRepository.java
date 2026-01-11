package com.api.liga.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.liga.model.Equipo;
import com.api.liga.model.Jugador;
import com.api.liga.model.Posicion;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

	Optional<Jugador> findByNombre(String nombre);

	List<Jugador> findByEquipoNombre(String nombreEquipo);

	List<Jugador> findByPosicion(Posicion posicion);

	List<Jugador> findByEquipo(Equipo equipo);

	List<Jugador> findByEquipoId(Long id);

	boolean existsByNombreAndEquipo(String nombre, Equipo equipo);

	boolean existsByDorsalAndEquipo(int dorsal, Equipo equipo);

	boolean existsByNombreAndEquipoAndIdNot(String nombre, Equipo equipo, Long id);

	boolean existsByDorsalAndEquipoAndIdNot(int dorsal, Equipo equipo, Long id);
}
