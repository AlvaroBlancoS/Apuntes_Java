package com.futbol.service;

import java.util.PriorityQueue;

import org.springframework.stereotype.Service;
/**
 * He creado un servicio sencillo para mantener los IDs eliminados y puede proporcionar
 * cuando es necesario.
 */
@Service
public class ReciclajeIDService {
	private PriorityQueue<Long> reciclarID = new PriorityQueue<>();

	public void agregarIDantiguo(Long id) {
		reciclarID.add(id);
	}

	public Long getReciclarID() {
		return reciclarID.isEmpty() ? null : reciclarID.poll();
	}
}
