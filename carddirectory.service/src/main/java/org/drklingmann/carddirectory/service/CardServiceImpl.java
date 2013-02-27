package org.drklingmann.carddirectory.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.drklingmann.carddirectory.domain.entities.cube.CardInCube;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.domain.entities.cube.CubeCardUse;
import org.drklingmann.carddirectory.domain.entities.cube.QCubeCardUse;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.domain.entities.game.QCardInSet;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.domain.entities.market.ModelPrice;
import org.drklingmann.carddirectory.domain.repository.cube.CardInCubeRepository;
import org.drklingmann.carddirectory.domain.repository.cube.CubeCardUseRepository;
import org.drklingmann.carddirectory.domain.repository.cube.CubeRepository;
import org.drklingmann.carddirectory.domain.repository.game.CardRepository;
import org.drklingmann.carddirectory.domain.repository.market.ModelPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

@Service(value = "cardService")
@Transactional(rollbackFor = Exception.class)
public class CardServiceImpl implements CardService {

	@Autowired
	CardRepository cardRepo;

	@Autowired
	CubeRepository cuberepo;
	
	@Autowired
	CardInCubeRepository cardInCuberepo;

	@Autowired
	CubeCardUseRepository cubeCardUserepo;
	
	@Autowired
	ModelPriceRepository modelPricerepo;
	
	@Override
	public List<Card> findAll() {
		return cardRepo.findAll();
	}

	@Override
	public List<Card> findAllFromSet(String name) {
		return cardRepo.findAllFromSetByName(name);
	}

	@Override
	public void fillMinPrices() {
//		List<ModelPrice> prices = modelPricerepo.findAll();
//		for (ModelPrice modelPrice : prices) {
//			Card card = modelPrice.getCardModel().getCardInSet().getCard();
//			if(card.getMinPrice()==null||card.getMinPrice()>modelPrice.getLow())
//				card.setMinPrice(modelPrice.getLow());
//		}
		
	}
	@Override
	public void fillMinPricesObjects() {
		List<ModelPrice> prices = modelPricerepo.findAll();
		for (ModelPrice modelPrice : prices) {
			Card card = modelPrice.getCardModel().getCardInSet().getCard();
			if(card.getPrice()==null||card.getPrice().getLow()>modelPrice.getLow())
				card.setPrice(modelPrice);
		}
		
	}
	private Integer countSaturation(Float price, Integer use) {
		if(price > 100)
			price = new Float(100);
		Double result = 100-Math.sqrt(10000 - ((price-100) * (price-100)));
		result *= -1;
		result += 100;
		
		result = ((use*2)-result)/2;
		
////		Double result = Math.sqrt(10000 - (price * price));
		
//		Double result = Math.log(price)/Math.log(logarytm);
//		result-= Math.log(0.02)/Math.log(logarytm);
//		result*= 136/(-(Math.log(0.02)/Math.log(logarytm))+Math.log(855)/Math.log(logarytm));
////		result*= -1;
////		result+= use;
//		System.out.println(result);
		result*= 2.55;
		return result.intValue();

		
	}

	@Override
	public List<CardWithSaturationAndUse> getCardsFromFilter(Set set, Color color, Float minPrice, Float maxPrice, Integer minUse, Integer maxUse, String rarity){
		QCubeCardUse use = QCubeCardUse.cubeCardUse;
		QCardInSet cardInSet = use.card.cardInSetCollection.any();
		BooleanBuilder filter = new BooleanBuilder();
		filter = filter.and(use.version.eq(130128));
		if(set!=null)
			filter = filter.and(cardInSet.set.eq(set));
		if(color!=null)
			filter = filter.and(use.card.color.eq(color));
		if(minPrice!=null)
			filter = filter.and(use.card.price.low.goe(minPrice));
		if(maxPrice!=null)
			filter = filter.and(use.card.price.low.loe(maxPrice));
		if(minUse!=null)
			filter = filter.and(use.cardUse.goe(minUse));
		if(maxUse!=null)
			filter = filter.and(use.cardUse.loe(maxUse));
		if(rarity!=null&&!rarity.equals(""))
			filter = filter.and(cardInSet.rarity.eq(rarity));
		return getNeededCards(cubeCardUserepo.findAll(filter));
	}
	
	@SuppressWarnings("unused")
	private static double logarytm = 1.2;//10;//2;
//	private static double dodawane = 5.64;//1.7;//5.64;
//	private static double mnozone = 8.84;//29.37;//8.84;
	@Override
	public List<CardWithSaturationAndUse> getNeededCardsNotSorted() {
		List<CubeCardUse> cardUses = cubeCardUserepo.findAllEagerFetchCard(130128);
		return getNeededCards(cardUses);
	}
	public List<CardWithSaturationAndUse> getNeededCards(Iterable<CubeCardUse> cardUses) {
		List<CardWithSaturationAndUse> cws = new ArrayList<CardWithSaturationAndUse>();
		for (CubeCardUse cubeCardUse : cardUses) {
			CardWithSaturationAndUse c = new CardWithSaturationAndUse();
			c.setUse(cubeCardUse.getCardUse());
			c.setColor(cubeCardUse.getCard().getColor());
			c.setName(cubeCardUse.getCard().getName());
			c.setPrice(cubeCardUse.getCard().getPrice());
			Integer saturation = countIntSaturation(cubeCardUse.getCard().getPrice(), cubeCardUse.getCardUse());
			c.setSaturationNumber(saturation);
			c.setSaturation(getHexSaturation(saturation));
			System.out.println(c.getName()+ " "+c.getUse());
			if(!cws.contains(c))
				cws.add(c);
		}
		Collections.sort(cws);
		return cws;		
	}
	
	private Integer countIntSaturation(ModelPrice price, Integer use) {
		Integer saturation = new Integer(-255);
		if(price!=null&&price.getLow()!=null) {
			saturation = countSaturation(price.getLow(), use);
		}
		return saturation;		
	}
	private String getHexSaturation(Integer saturation) {
		if(saturation>255)
			saturation=255;
		if(saturation<-255)
			saturation=255;
		String hex = "";
		if(saturation<0) {
			hex = Integer.toHexString(255+saturation);
			if (hex.length()==1)
				hex = "0"+hex;
			return hex+"FFFF";
		} else {
			hex = Integer.toHexString(255-saturation);
			if (hex.length()==1)
				hex = "0"+hex;
			return "FF"+hex+hex;
		}
	}

//	@Override
//	public List<CardWithSaturationAndUse> getNeededCardsSortedByUse() {
//		List<CubeCardUse> cardUses = cubeCardUseDao.findAllSortedByUse();
//		return getNeededCards(cardUses);
//	}
//
//	@Override
//	public List<CardWithSaturationAndUse> findAllCubeNeedsFromSet(String name) {
//		List<CubeCardUse> cardUses = cubeCardUseDao.findAllSortedFromSet(name);
//		return getNeededCards(cardUses);
//	}

	@Override
	public List<CardWithSaturationAndUse> findAllCubeNeedsFromSetSorted(String name) {
		List<CardWithSaturationAndUse> cardwithSaturationList = cubeCardUserepo.findAllCubeNeedsFromSetSorted(name,130128);
		for (CardWithSaturationAndUse cardWithSaturationAndUse : cardwithSaturationList) {
			Integer saturation = countIntSaturation(cardWithSaturationAndUse.getPrice(), cardWithSaturationAndUse.getUse());
			cardWithSaturationAndUse.setSaturationNumber(saturation);
			cardWithSaturationAndUse.setSaturation(getHexSaturation(saturation));			
		}
		Collections.sort(cardwithSaturationList);
		return cardwithSaturationList;
	}

	@Override
	public void insertCardToCube(String cardName, String cubeName) {
		CardInCube cic = new CardInCube();
		cic.setCard(cardRepo.findByName(cardName));
		cic.setCube(cuberepo.findByName(cubeName));
		cardInCuberepo.save(cic);
	}

	@Override
	public List<CardWithSaturationAndUse> getCubeCardsSorted() {
		List<CubeCardUse> cardUses = cubeCardUserepo.findAllFromCubeEagerFetchCard(130128);
		return getNeededCards(cardUses);
	}

	@Override
	public List<CardWithSaturationAndUse> getCubeCardsSorted(Color color) {
		List<CubeCardUse> cardUses = cubeCardUserepo.findAllFromCubeEagerFetchCardByColor(color,130128);
		return getNeededCards(cardUses);
	}

	@Override
	public List<CardWithSaturationAndUse> getCubeCardsSorted(int lower,
			int upper) {
		List<CubeCardUse> cardUses = cubeCardUserepo.findAllFromCubeEagerFetchCardByRange(lower, upper,130128);
		return getNeededCards(cardUses);
	}

	@Override
	public List<CardWithSaturationAndUse> getCubeCardsSorted(String rarity) {
		List<CubeCardUse> cardUses = cubeCardUserepo.findAllEagerFetchCardByRarity(rarity+"%",130128);
		return getNeededCards(cardUses);
	}

}
