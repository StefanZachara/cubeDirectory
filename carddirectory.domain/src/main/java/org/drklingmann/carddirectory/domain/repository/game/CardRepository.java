package org.drklingmann.carddirectory.domain.repository.game;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CardRepository extends JpaRepository<Card, Long> {
	Card findByName(String name);
	
	@Query("SELECT c FROM Set s JOIN s.cardInSetCollection cis JOIN cis.card c WHERE s.name =?1")
	List<Card> findAllFromSetByName(String setName);
}
