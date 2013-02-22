package org.drklingmann.carddirectory.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.drklingmann.carddirectory.domain.entities.cube.CubeCardUse;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.CardInSet;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.domain.entities.market.CardModel;
import org.drklingmann.carddirectory.domain.entities.market.ModelPrice;
import org.drklingmann.carddirectory.domain.repository.cube.CubeCardUseRepository;
import org.drklingmann.carddirectory.domain.repository.game.CardInSetRepository;
import org.drklingmann.carddirectory.domain.repository.game.CardRepository;
import org.drklingmann.carddirectory.domain.repository.game.SetRepository;
import org.drklingmann.carddirectory.domain.repository.market.CardModelRepository;
import org.drklingmann.carddirectory.domain.repository.market.ModelPriceRepository;
import org.drklingmann.carddirectory.webparser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "parserService")
@Transactional(rollbackFor = Exception.class)
public class ParserServiceImpl implements ParserService {

	@Autowired
	private CubeCardUseRepository cubeCardUseRepo;

	@Autowired
	private SetRepository setrepo;

	@Autowired
	private ModelPriceRepository modelPricerepo;

	@Autowired
	private CardModelRepository cardModelrepo;

	@Autowired
	private CardInSetRepository cardInSetRepo;
	
	@Autowired
	private CardRepository cardRepo;

	@Autowired
	private Parser parser;

	@Override
	public void insertSets() {
		try {
			List<Set> sets = parser.getSetListFromMagicCards();
			for (Set set : sets) {
				Set set1 = setrepo.findByName(set.getName());
				if(set1==null)
					setrepo.save(set);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
//			throw new Exception(e);
		}

	}
	
	@Override
	public void fillNewPrices(Integer version) {
		try {
			Date now = new Date();
			int i=0;
			List<CardModel> linkModels = cardModelrepo.findAllInCurrentUseVersion(version);
			HashMap<String, Card> cardsToUpdate = new HashMap<String, Card>();
			for (CardModel cardModel : linkModels) {
				i++;
				if(i==200||i==500||i==800||i==1400||i==2000)
					System.out.println(i);
				ModelPrice price = parser.getNewPriceForModel(cardModel, now);
				if(price!=null) {
					Card cardToModify = cardsToUpdate.get(cardModel.getCardInSet().getCard().getName());
					if(cardToModify==null) {
						cardToModify = cardModel.getCardInSet().getCard();
						cardsToUpdate.put(cardToModify.getName(), cardToModify);
					}
					if(cardToModify.getPrice()==null)
						cardToModify.setPrice(price);
					else if (cardToModify.getPrice().getDate().before(price.getDate())) {
						cardToModify.setPrice(price);
					} else if (cardToModify.getPrice().getDate().equals(price.getDate())
								&&
								cardToModify.getPrice().getLow()>price.getLow()) {
						cardToModify.setPrice(price);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
//			throw new Exception(e);
		}

	}

	@Override
	public String insertCardsFromSet(String setName, Integer initial, Integer finall) {
		
		Set set = setrepo.findByName(setName);
		try {
			List<CardModel> modelPrices = parser.getPricesForConcreteSet(set, initial, finall);
			System.out.println("pobralismy model - prices");
			for (CardModel cardModel : modelPrices) {
				Card oldCard = cardRepo.findByName(cardModel.getCardInSet().getCard().getName());
				if(oldCard==null)
					cardRepo.save(cardModel.getCardInSet().getCard());
				else
					cardModel.getCardInSet().setCard(oldCard);
				CardInSet cis = cardInSetRepo.findBySetAndCard(cardModel.getCardInSet().getSet(), cardModel.getCardInSet().getCard());
				if(cis!=null)
					cardModel.setCardInSet(cis);
				else
					cardInSetRepo.save(cardModel.getCardInSet());
				CardModel oldModel = cardModelrepo.findByMcLink(cardModel.getMcLink());
				if(oldModel==null) {
					cardModelrepo.save(cardModel);
					if(cardModel.getPriceCollection()!=null&&cardModel.getPriceCollection().size()!=0) {
						for (ModelPrice price : cardModel.getPriceCollection()) {
							modelPricerepo.save(price);
							ModelPrice cardPriceNo1 = cardModel.getCardInSet().getCard().getPrice();
							if(cardPriceNo1==null||cardPriceNo1.getLow()>price.getLow())
								cardModel.getCardInSet().getCard().setPrice(price);
						}
					}
				}
			}
			return "Sukces!";
		} catch (IOException e) {
			return e.getMessage();
			//e.printStackTrace();
//			throw new Exception(e);
		}
		
	}

	@Override
	public void insertUse(Integer version) {
		try {
			List<CubeCardUse> useList = parser.useList(version);
			for (CubeCardUse cubeCardUse : useList) {
//				try {
					cubeCardUse.setCard(cardRepo.findByName(cubeCardUse.getCard().getName()));
					CubeCardUse ccu = cubeCardUseRepo.findByVersionAndCard(cubeCardUse.getVersion(), cubeCardUse.getCard());
					if(ccu==null)
						cubeCardUseRepo.save(cubeCardUse);
					else {
						ccu.setCardUse(ccu.getCardUse()+cubeCardUse.getCardUse());
						cubeCardUseRepo.save(ccu);
					}
//				} catch (EntityExistsException e) {
//					Integer tempUse = cubeCardUse.getUse();
//					cubeCardUse = cubeCardUseDao.findByIds(cubeCardUse.getCard().getId(), cubeCardUse.getVersion());
//					cubeCardUse.setUse(cubeCardUse.getUse()+tempUse);
//					cubeCardUseDao.merge(cubeCardUse);
//					System.out.println("Duplikat: " + cubeCardUse.getCard().getName());
//				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
