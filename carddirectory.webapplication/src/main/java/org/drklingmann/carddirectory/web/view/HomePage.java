package org.drklingmann.carddirectory.web.view;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.service.CardService;
import org.drklingmann.carddirectory.service.ParserService;
import org.drklingmann.carddirectory.service.UserService;
import org.drklingmann.carddirectory.web.BasePage;
import org.drklingmann.carddirectory.web.component.CubeNeedsPanel;

public class HomePage extends BasePage {
	
	private static final long serialVersionUID = 1L;
	@SpringBean
	private UserService userService;
	@SpringBean
	private CardService cardService;
	@SpringBean
	private ParserService parserService;

	private CubeNeedsPanel needsTable;
	
	public HomePage() {
		super();
//		needsTable = new CubeNeedsPanel("cardList", cardList);
		initGui();
	}
	public HomePage(List<CardWithSaturationAndUse> cardList) {
		super(cardList);
//		needsTable = new CubeNeedsPanel("cardList", cardList);
		initGui();
	}
	private void initGui() {

		needsTable.setOutputMarkupId(true);
		add(needsTable);
//		add(new CardsFilterForm("filterForm", new FilterResults(), needsTable));
		add(new Link<String>("getSets") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				parserService.insertSets();
			}
		});
		add(new Link<String>("getCardUseInCube") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				parserService.insertUse(130128);
			}
		});
		add(new Link<String>("fillMinimalPricesForCards") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				cardService.fillMinPricesObjects();
			}
		});
		add(new Link<String>("fillVersionPrices") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				parserService.fillNewPrices(130128);
			}
		});
		add(new Link<String>("goToCubeNeeds") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				List<CardWithSaturationAndUse> list = cardService.getCardsFromFilter(
						null, 
						null, 
						null, 
						null, 
						new Integer(93), 
						null, 
						null);
				setResponsePage(new HomePage(list));
			}
		});
		add(new Label("helloLabel", "No witka"));//parserService.insertCardsFromSet("Return to Ravnica")));
		add(new Label("currentTime", new Date().toString()));
		
		add(new Label("numberOfUsers", userService.size() +""));
	}
	@Override
	protected CubeNeedsPanel getListPanel(List<CardWithSaturationAndUse> list) {
		needsTable = new CubeNeedsPanel("cardList", list);
		return needsTable;
	}

}
