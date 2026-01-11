package com.api.liga.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.liga.exceptions.RecursoDuplicado;
import com.api.liga.exceptions.RecursoNoEncontrado;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNoEncontrado.class)
	public ResponseEntity<String> manejarRecursoNoEncontrado(RecursoNoEncontrado ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(RecursoDuplicado.class)
	public ResponseEntity<String> manejarRecursoDuplicado(RecursoDuplicado ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}
}
