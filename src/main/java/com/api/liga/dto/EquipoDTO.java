package com.api.liga.dto;

import java.time.LocalDate;

public class EquipoDTO {

	private Long id;
	private String nombre;
	private LocalDate fechaFundacion;
	private String nombreEstadio;
	private int capacidad;
	private String nombreEntrenador;

	public EquipoDTO() {
	}

	public EquipoDTO(Long id, String nombre, LocalDate fechaFundacion, String nombreEstadio, int capacidad,
			String nombreEntrenador) {
		this.id = id;
		this.nombre = nombre;
		this.fechaFundacion = fechaFundacion;
		this.nombreEstadio = nombreEstadio;
		this.capacidad = capacidad;
		this.nombreEntrenador = nombreEntrenador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(LocalDate fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getNombreEntrenador() {
		return nombreEntrenador;
	}

	public void setNombreEntrenador(String nombreEntrenador) {
		this.nombreEntrenador = nombreEntrenador;
	}
}
