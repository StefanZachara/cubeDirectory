package org.drklingmann.carddirectory.service;

public interface ParserService {

	void insertSets();

	String insertCardsFromSet(String string, Integer initial, Integer finall);

	void insertUse(Integer i);

	void fillNewPrices(Integer version);
	
}
