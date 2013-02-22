package org.drklingmann.carddirectory.service;

import java.util.List;

import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.Color;

public interface CardService {

	public List<Card> findAll();

	public List<Card> findAllFromSet(String name);
	
	public void fillMinPrices();

	public void fillMinPricesObjects();
//
	public List<CardWithSaturationAndUse> getNeededCardsNotSorted();

//	public List<CardWithSaturationAndUse> getNeededCardsSortedByUse();
//
//	public List<CardWithSaturationAndUse> findAllCubeNeedsFromSet(String setName);

	public void insertCardToCube(String cardName, String string);

	public List<CardWithSaturationAndUse> findAllCubeNeedsFromSetSorted(String setName);

	public List<CardWithSaturationAndUse> getCubeCardsSorted();

	public List<CardWithSaturationAndUse> getCubeCardsSorted(Color color);

	public List<CardWithSaturationAndUse> getCubeCardsSorted(int lower, int upper);

	public List<CardWithSaturationAndUse> getCubeCardsSorted(String rarity);
}
