package org.drklingmann.carddirectory.domain.repository.cube;

import org.drklingmann.carddirectory.domain.entities.cube.CardInCube;
import org.drklingmann.carddirectory.domain.entities.cube.Cube;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInCubeRepository extends JpaRepository<CardInCube, Long>{
	CardInCube findByCardAndCube(Card card, Cube cube);
}
