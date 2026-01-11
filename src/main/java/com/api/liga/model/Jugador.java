package com.api.liga.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "jugadores")
public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nombre;

	public void setId(Long id) {
		this.id = id;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@NotNull
	@Column(nullable = false)
	private LocalDate fechaNacimiento;

	@Min(value = 1, message = "El dorsal debe ser un número entre 1 y 99")
	@Max(value = 99, message = "El dorsal debe ser un número entre 1 y 99")
	@Column(nullable = false)
	private int dorsal;

	@NotNull
	@Min(value = 0, message = "El salario no puede ser negativo")
	@Column(nullable = false)
	private BigDecimal salario;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Posicion posicion;

	@ManyToOne(optional = false)
	@JoinColumn(name = "equipo_id", nullable = false)
	@JsonIgnoreProperties("jugadores")
	private Equipo equipo;

	@Column(nullable = false)
	private int edad;

	public Jugador() {

	}

	public Jugador(String nombre, LocalDate fechaNacimiento, int dorsal, BigDecimal salario, Posicion posicion,
			Equipo equipo) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.dorsal = dorsal;
		this.salario = salario;
		this.posicion = posicion;
		this.equipo = equipo;
	}

	// ESTE METODO SIRVE PARA CALCULAR LA EDAD AUTOMATICAMENTE, NO ES UN CAMPO DEL OBJETO, ES UN METODO
	@PrePersist
	@PreUpdate
	private void calcularEdad() {
		if (fechaNacimiento != null) {
			this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
		}
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getDorsal() {
		return dorsal;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public int getEdad() {
		return edad;
	}

	// Setters

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
}
