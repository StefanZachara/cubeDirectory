package org.drklingmann.carddirectory.domain.repository.game;

import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.CardInSet;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInSetRepository extends JpaRepository<CardInSet, Long> {

	CardInSet findBySetAndCard(Set set, Card card);
}
