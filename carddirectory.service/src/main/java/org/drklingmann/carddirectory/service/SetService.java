package org.drklingmann.carddirectory.service;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.game.Set;

public interface SetService {

	void persist(Set s);
	public List<Set> findAll();
}
