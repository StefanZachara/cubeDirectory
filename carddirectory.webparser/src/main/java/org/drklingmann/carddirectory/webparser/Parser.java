package org.drklingmann.carddirectory.webparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.drklingmann.carddirectory.domain.entities.cube.CubeCardUse;
import org.drklingmann.carddirectory.domain.entities.game.Card;
import org.drklingmann.carddirectory.domain.entities.game.CardInSet;
import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.domain.entities.market.CardModel;
import org.drklingmann.carddirectory.domain.entities.market.ModelPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service(value = "parser")
public class Parser {

	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.194.6.34", 8080)); // or whatever your proxy is

	public List<CubeCardUse> useList(Integer version) throws IOException {
	    Document document = getDocument("http://forums.mtgsalvation.com/showthread.php?t=271371", proxy);

	    List<CubeCardUse> cardUseList = new ArrayList<CubeCardUse>();
		    
		Elements cards = document.select("#post_message_5826880 .alt2 span").first().select("a");//("a:lt(50)");
		for (Element cardEl : cards) {
			String numberOfOccurancesAsString = cardEl.previousSibling().toString();
			Card card = new Card();
			card.setName(cardEl.text().replace("AE", "Æ"));
			CubeCardUse use = new CubeCardUse();
			use.setCard(card);
			use.setCardUse(Integer.parseInt(numberOfOccurancesAsString.substring(0, numberOfOccurancesAsString.length()-2)));
			use.setVersion(version);
			cardUseList.add(use);
			
		}
		return cardUseList;
	}

	public List<Set> getSetListFromMagicCards() throws IOException {		  
		  Document document = getDocument("http://magiccards.info/sitemap.html", proxy);
		  
		  List<Set> ss = new ArrayList<Set>();
		  
		  List<Element> sets = document.select("li a[href*=/en.html]");//.subList(111, 112);
		  for (Element element : sets) {
			Set s = new Set();
			if(!element.text().contains("MTGO")) {
				s.setName(element.text());
				s.setMcshort(((Element)element.nextSibling().nextSibling()).text());
				ss.add(s);
			}
		  }
		  return ss;
		
	}
	public List<CardModel> getPricesForConcreteSet(Set set, Integer initial, Integer finall) throws IOException {
		Document doc = getDocument("http://magiccards.info/"+set.getMcshort()+"/en.html", proxy);

		List<CardModel> cards = new ArrayList<CardModel>();
		List<Element> cardss = doc.select("tr td a[href*=/en/]");//.subList(initial, finall);
		
		for (Element element : cardss) {
			CardModel card = getModelFromURL(proxy, element.attr("href"), set);
			String type = "";
			String mana = "";
			Node nextNode = element.parent().nextSibling().nextSibling();
			if (nextNode.childNodes().size()!=0)
				type = nextNode.childNode(0).toString();//card.setType(nextNode.childNode(0).toString());
			nextNode = nextNode.nextSibling().nextSibling();
			if (nextNode.childNodes().size()!=0)
				mana = nextNode.childNode(0).toString();//card.setMana(nextNode.childNode(0).toString());
			nextNode = nextNode.nextSibling().nextSibling();
			if (nextNode.childNodes().size()!=0)
				card.getCardInSet().setRarity(nextNode.childNode(0).toString());
			nextNode = nextNode.nextSibling().nextSibling();
			if (nextNode.childNodes().size()!=0)
				;//card.setArtist(nextNode.childNode(0).toString());
			card.getCardInSet().getCard().setColor(getColor(type,mana));
			cards.add(card);
		}
		return cards;
	}
	private Color getColor(String type, String mana) {
		if (type.contains("Land"))
			return Color.LAND;
		if (mana.contains("{"))
			return Color.HYBRID;
		if (mana.contains("W")) {
			if (mana.contains("U"))
				return Color.MULTICOLOR;
			if (mana.contains("B"))
				return Color.MULTICOLOR;
			if (mana.contains("R"))
				return Color.MULTICOLOR;
			if (mana.contains("G"))
				return Color.MULTICOLOR;
			return Color.WHITE;
		}
		if (mana.contains("U")) {
			if (mana.contains("B"))
				return Color.MULTICOLOR;
			if (mana.contains("R"))
				return Color.MULTICOLOR;
			if (mana.contains("G"))
				return Color.MULTICOLOR;
			return Color.BLUE;
		}
		if (mana.contains("B")) {
			if (mana.contains("R"))
				return Color.MULTICOLOR;
			if (mana.contains("G"))
				return Color.MULTICOLOR;
			return Color.BLACK;
		}
		if (mana.contains("R")) {
			if (mana.contains("G"))
				return Color.MULTICOLOR;
			return Color.RED;
		}
		if (mana.contains("G")) {
			return Color.GREEN;
		}
		if (type.contains("Artifact"))
			return Color.ARTIFACT;
		return Color.COLORLESS;
	}
	private CardModel getModelFromURL(Proxy proxy, String cardLink, Set set) throws IOException {
		Document doc = getDocument("http://magiccards.info"+cardLink, proxy);

		CardModel model = new CardModel();
		model.setCardInSet(new CardInSet());
		model.getCardInSet().setSet(set);
		model.getCardInSet().setCard(new Card());
		
		model.getCardInSet().getCard().setName(doc.select("span[style=font-size: 1.5em;] a").text());
		model.setMcLink("http://magiccards.info"+doc.select("span[style=font-size: 1.5em;] a").attr("href"));
		try {
			String priceSite = doc.select("script[src~=tcgplayer]").get(1).attr("src").toString();
			ModelPrice price = getPrice(priceSite,proxy);
			List<ModelPrice> prices = new ArrayList<ModelPrice>();
			prices.add(price);
			price.setCardModel(model);
			model.setPriceCollection(prices);
		} catch (Exception e) {
			//nie dodajemy ceny, bo nie bylo
		}
		model.setPrinting(doc.select("u:has(b:contains(Printing)) ~ b").first().text());
		
		return model;
	}
	
	public ModelPrice getNewPriceForModel(CardModel model, Date date) throws IOException {
		Document doc = getDocument(model.getMcLink(), proxy);
		try {
			String priceSite = doc.select("script[src~=tcgplayer]").get(1).attr("src").toString();
			ModelPrice price = getPrice(priceSite,proxy);
			model.getPriceCollection().add(price);
			price.setCardModel(model);
			price.setDate(date);
			return price;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	private ModelPrice getPrice(String priceURL, Proxy proxy) throws IOException {
		return getPrice(priceURL, proxy, new Date());
	}
	private ModelPrice getPrice(String priceURL, Proxy proxy, Date date) throws IOException {
	    Document document = getDocument(priceURL, proxy, true);
	    ModelPrice price = new ModelPrice();
	    String priceTemp = document.select(".TCGPPriceLow").first().text();
	    if(!priceTemp.contains("$"))
	    	return null;
	    price.setLow(Float.parseFloat(priceTemp.replace("$", "")));
	    price.setMid(Float.parseFloat(document.select(".TCGPPriceMid").first().text().replace("$", "")));
	    price.setHigh(Float.parseFloat(document.select(".TCGPPriceHigh").first().text().replace("$", "")));
	    price.setDate(date);
	    
	    return price;
}
	
	private Document getDocument(String site, Proxy proxy) throws IOException {
		return getDocument(site, proxy, false);
	}
	private Document getDocument(String site, Proxy proxy, boolean replace) throws IOException {
		  URL url = new URL(site);
		  HttpURLConnection uc = (HttpURLConnection)url.openConnection();//proxy));

		  uc.connect();
		    String line = null;
		    StringBuffer tmp = new StringBuffer();
		    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		    while ((line = in.readLine()) != null) {
		      tmp.append(line);
		    }
		    if(replace){
			    String temp = StringUtils.replaceEach(String.valueOf(tmp), new String[]{"'+'","\\'"}, new String[]{"","'"});
			    temp = temp.substring(16, temp.length()-3);
			    return Jsoup.parse(temp);
		    }
		  return Jsoup.parse(String.valueOf(tmp));
		
	}

}
