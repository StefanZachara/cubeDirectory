package org.drklingmann.carddirectory.domain.repository.market;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.market.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardModelRepository extends JpaRepository<CardModel, Long>{
	CardModel findByMcLink(String link);

	@Query("select cm from CubeCardUse ccu JOIN ccu.card.cardInSetCollection cis JOIN cis.cardModelCollection cm where ccu.version =?1")
	List<CardModel> findAllInCurrentUseVersion(Integer version);

	@Query("SELECT cm FROM CardModel cm WHERE cm.cardInSet.card.name =?1")
	List<CardModel> findMutavault(String name);
}
