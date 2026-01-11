package com.api.liga.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "equipos")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String nombre;

	@NotNull
	@Column(nullable = false)
	private LocalDate fechaFundacion;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String nombreEstadio;

	@Min(value = 0, message = "La capacidad no puede ser inferior a 0")
	private int capacidad;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String nombreEntrenador;

	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Jugador> jugadores = new ArrayList<>();

	public Equipo() {

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Equipo(String nombre, LocalDate fechaFundacion, String nombreEstadio, int capacidad,
			String nombreEntrenador) {
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.nombreEstadio = nombreEstadio;
		this.capacidad = capacidad;
		this.nombreEntrenador = nombreEntrenador;
	}

	public void addJugador(Jugador jugador) {
		jugadores.add(jugador);
		jugador.setEquipo(this);
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getNombreEntrenador() {
		return nombreEntrenador;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	// Setters

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public void setNombreEntrenador(String nombreEntrenador) {
		this.nombreEntrenador = nombreEntrenador;
	}
}
