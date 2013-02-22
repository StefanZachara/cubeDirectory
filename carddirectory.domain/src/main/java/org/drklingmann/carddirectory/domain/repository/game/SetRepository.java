package org.drklingmann.carddirectory.domain.repository.game;

import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<Set, Long>{
	Set findByName(String name);
}
