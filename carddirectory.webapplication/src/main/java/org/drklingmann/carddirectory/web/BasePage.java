package org.drklingmann.carddirectory.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.drklingmann.carddirectory.domain.entities.cube.CardWithSaturationAndUse;
import org.drklingmann.carddirectory.domain.entities.game.Color;
import org.drklingmann.carddirectory.domain.entities.game.Set;
import org.drklingmann.carddirectory.service.CardService;
import org.drklingmann.carddirectory.service.SetService;
import org.drklingmann.carddirectory.web.view.CardsInSetPage;
import org.drklingmann.carddirectory.web.view.CubeNeedsPage;

public abstract class BasePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2653420841066715010L;
	@SpringBean
	private SetService setService;
	@SpringBean
	private CardService cardService;

	public BasePage() {
		super();
		add(new Link<Void>("home") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1329109319785765596L;

			@Override
			public void onClick() {
				setResponsePage(CubeNeedsPage.class);			
			}
			
		});
//		add(new CardListLink("cube",cardService.getCubeCardsSorted()));
//		add(new CardListLink("r0",cardService.getCubeCardsSorted("Common")));
//		add(new CardListLink("r1",cardService.getCubeCardsSorted("Uncommon")));
//		add(new CardListLink("r2",cardService.getCubeCardsSorted("Rare")));
//		add(new CardListLink("r3",cardService.getCubeCardsSorted("Mythic Rare")));
//		add(new CardListLink("r4",cardService.getCubeCardsSorted("Special")));
//		add(new CardListLink("c0",cardService.getCubeCardsSorted(Color.COLORLESS)));
//		add(new CardListLink("c1",cardService.getCubeCardsSorted(Color.WHITE)));
//		add(new CardListLink("c2",cardService.getCubeCardsSorted(Color.BLUE)));
//		add(new CardListLink("c3",cardService.getCubeCardsSorted(Color.BLACK)));
//		add(new CardListLink("c4",cardService.getCubeCardsSorted(Color.RED)));
//		add(new CardListLink("c5",cardService.getCubeCardsSorted(Color.GREEN)));
//		add(new CardListLink("c6",cardService.getCubeCardsSorted(Color.MULTICOLOR)));
//		add(new CardListLink("c7",cardService.getCubeCardsSorted(Color.HYBRID)));
//		add(new CardListLink("c8",cardService.getCubeCardsSorted(Color.ARTIFACT)));
//		add(new CardListLink("c9",cardService.getCubeCardsSorted(Color.LAND)));
//		add(new CardListLink("w0",cardService.getCubeCardsSorted(90,100)));
//		add(new CardListLink("w1",cardService.getCubeCardsSorted(80,90)));
//		add(new CardListLink("w2",cardService.getCubeCardsSorted(70,80)));
//		add(new CardListLink("w3",cardService.getCubeCardsSorted(60,70)));
		add(new PropertyListView<Set>("sets",setService.findAll()){
			/**
			 * 
			 */
			private static final long serialVersionUID = -6804340157852301555L;

			@Override
			protected void populateItem(ListItem<Set> item) {
				Link<Set> link = new Link<Set>("link", item.getModel()) {
					/**
					 * 
					 */
					private static final long serialVersionUID = -457551712845071160L;

					@Override
					public void onClick() {
						setResponsePage(new CardsInSetPage(getModelObject()));
					}
				};
				link.add(new Label("name"));
				item.add(link);
			}
		});
	}
	
	private class CardListLink extends Link<Void>{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6108146687580321551L;
		List<CardWithSaturationAndUse> cardListPerm;
		public CardListLink(String name, List<CardWithSaturationAndUse> cardList) {
			super(name);
			cardListPerm = cardList;
		}
		@Override
		public void onClick() {
			setResponsePage(new CubeNeedsPage(cardListPerm));			
		}
		
	}

	public BasePage(PageParameters parameters) {
		super(parameters);
	}
	
}
