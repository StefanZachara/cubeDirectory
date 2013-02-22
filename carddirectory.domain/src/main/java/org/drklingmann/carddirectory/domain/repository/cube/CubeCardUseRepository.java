package org.drklingmann.carddirectory.domain.repository.cube;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.domain.entities.cube.CubeCardUse;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CubeCardUseRepository extends
		JpaRepository<CubeCardUse, Long> {
	
	CubeCardUse findByVersionAndCard(Integer version, Card card);
	
	@Query("select c " +
				"from CubeCardUse c join fetch c.card " +
				"where c.cardUse > 20 " +
				"and not exists (select 1 from CardInCube cic where cic.card = c.card) " +
				"and c.version = ?1 " +
//				"and c.card.color = 0 " +
				"order by c.cardUse desc")
	List<CubeCardUse> findAllEagerFetchCard(Integer version);
	
	@Query("select c " +
				"from CubeCardUse c join fetch c.card " +
				"where c.cardUse > 20 " +
				"and not exists (select 1 from CardInCube cic where cic.card = c.card) " +
				"and exists (select 1 from CardInSet cis where cis.rarity like ?1 and cis.card = c.card) " +
				"and c.version = ?2 " +
//				"and c.card.color = 0 " +
				"order by c.cardUse desc")
	List<CubeCardUse> findAllEagerFetchCardByRarity(String rarity, Integer version);
	
	@Query("select NEW org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse(" +
			"c.name, u.cardUse, c.price, c.color, cis.rarity) " +
			"from CubeCardUse u join u.card c join c.cardInSetCollection cis JOIN cis.set s " +
			"WHERE s.name = ?1 " +
			"and u.cardUse > 10 " +
			"and not exists (select 1 from CardInCube cic where cic.card = c) " +
			"and u.version = ?2 " +
			"order by cis.rarity, c.color, c.name")
	List<CardWithSaturationAndUse> findAllCubeNeedsFromSetSorted(String setName, Integer version);

	@Query("select c " +
			"from CubeCardUse c join fetch c.card " +
			"where c.cardUse > 10 " +
			"and exists (select 1 from CardInCube cic where cic.card = c.card) " +
			"and c.version = ?1 " +
//			"and c.card.color = 0 " +
			"order by c.cardUse desc")
	List<CubeCardUse> findAllFromCubeEagerFetchCard(Integer version);

	@Query("select c " +
			"from CubeCardUse c join fetch c.card " +
			"where c.cardUse > 20 " +
			"and not exists (select 1 from CardInCube cic where cic.card = c.card) " +
			"and c.card.color = ?1 " +
			"and c.version = ?2 " +
			"order by c.cardUse desc")
	List<CubeCardUse> findAllFromCubeEagerFetchCardByColor(Color color, Integer version);

	@Query("select c " +
			"from CubeCardUse c join fetch c.card " +
			"where c.cardUse > 20 " +
			"and not exists (select 1 from CardInCube cic where cic.card = c.card) " +
			"and c.cardUse > ?1 " +
			"and c.cardUse <= ?2 " +
			"and c.version = ?3 " +
			"order by c.cardUse desc")
	List<CubeCardUse> findAllFromCubeEagerFetchCardByRange(int lower, int upper, Integer version);
	
//	@Override
//	public List<CubeCardUse> findAllSortedByUse() {
//		return getEntityManager().
//			createQuery(
//					"select c " +
//					"from CubeCardUse c join fetch c.card " +
//					"where c.cardUse > 20 " +
//					"and not exists (select 1 from CardInCube cic where cic.card = c.card) " +
//					"order by c.cardUse", getClazz()).getResultList();			
//	}
//
//	@Override
//	public List<CubeCardUse> findAllSortedFromSet(String setName) {
//		return getEntityManager().
//				createQuery(
//						"select u " +
//						"from CubeCardUse u join fetch u.card c join c.cardInSetCollection cis JOIN cis.set s " +
//						"WHERE s.name =:name " +
//						"and u.cardUse > 10 " +
//						"and not exists (select 1 from CardInCube cic where cic.card = c) " +
//						"order by c.color, c.name", getClazz()).
//				setParameter("name", setName).
//				getResultList();			
//	}
//	
//	@Override
//	public List<CardWithSaturationAndUse> findAllCardsWithSaturationSortedFromSet(String setName) {
//		return getEntityManager().
//				createQuery(
//						"select NEW org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse(" +
//						"c.name, u.cardUse, c.minPrice, c.color, cis.rarity) " +
//						"from CubeCardUse u join u.card c join c.cardInSetCollection cis JOIN cis.set s " +
//						"WHERE s.name =:name " +
//						"and u.cardUse > 10 " +
//						"and not exists (select 1 from CardInCube cic where cic.card = c) " +
//						"order by cis.rarity, c.color, c.name", CardWithSaturationAndUse.class).
//				setParameter("name", setName).
//				getResultList();			
//	}

}
