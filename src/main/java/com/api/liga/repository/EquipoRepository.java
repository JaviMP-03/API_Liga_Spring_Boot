package com.api.liga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.liga.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

	Optional<Equipo> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

	boolean existsByNombreEstadio(String nombreEstadio);

	boolean existsByNombreEntrenador(String nombreEntrenador);

	boolean existsByNombreAndIdNot(String nombre, Long id);

	boolean existsByNombreEstadioAndIdNot(String nombreEstadio, Long id);

	boolean existsByNombreEntrenadorAndIdNot(String nombreEntrenador, Long id);
}
