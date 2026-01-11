package com.api.liga.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.liga.model.Posicion;

public class JugadorDTO {

	private Long id;

	private String nombre;

	private LocalDate fechaNacimiento;

	private int dorsal;

	private BigDecimal salario;

	private Posicion posicion;

	private String equipo; // nombre del equipo

	private int edad;

	public JugadorDTO() {
	}

	public JugadorDTO(Long id, String nombre, LocalDate fechaNacimiento, int dorsal, BigDecimal salario,
			Posicion posicion, String equipo, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.dorsal = dorsal;
		this.salario = salario;
		this.posicion = posicion;
		this.equipo = equipo;
		this.edad = edad;
	}

	// Getters y Setters
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}
